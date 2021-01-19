import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.aspose.psd.Image;
import com.aspose.psd.fileformats.psd.PsdImage;
import com.aspose.psd.fileformats.psd.layers.TextLayer;

public class FontSearch
{

	public static void main(String[] args)
	{
		File dir = new File(System.getProperty("user.dir"));
		PsdImage psdImage;
		ArrayList<String> list = new ArrayList<String>();

		File[] array = dir.listFiles();

		for (int x = 0; x < array.length; x++)
		{
			int y = array[x].getName().lastIndexOf(".");
			if (array[x].getName().substring(y + 1).equals("psd"))
			{

				try
				{
				System.out.println(array[x].getName());

					psdImage = (PsdImage) Image.load(array[x].toString());

					for (int z = 0; z < psdImage.getLayers().length; z++)
					{
						if (psdImage.getLayers()[z] instanceof TextLayer)
						{
							TextLayer layer = (TextLayer) psdImage.getLayers()[z];
							String Font = layer.getFont().getName();

							if (!list.contains(Font))
							{
								list.add(Font);
							}

						}
					}

					for (int z = 0; z < list.size(); z++)
					{
						System.out.println("\t" + list.get(z));
					}
					list.clear();
				} catch (Exception e)
				{
					System.out.println(e);
				}

			}
		}

	}

}
