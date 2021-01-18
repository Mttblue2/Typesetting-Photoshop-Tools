import java.io.File;
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

		File[] array = dir.listFiles();

		for (int x = 0; x < array.length; x++)
		{
			int y = array[x].toString().lastIndexOf(".");
			if (array[x].toString().substring(y + 1).equals("psd"))
			{

				try
				{
					int q = array[x].toString().lastIndexOf("\\");
					System.out.println(array[x].toString().substring(q+1));
					
					psdImage = (PsdImage) Image.load(array[x].toString());

					for (int z = 0; z < psdImage.getLayers().length; z++)
					{
						if (psdImage.getLayers()[z] instanceof TextLayer)
						{
							TextLayer layer = (TextLayer) psdImage.getLayers()[z];
							String Font = layer.getFont().toString();
							System.out.println("\t" + Font.substring(Font.indexOf("=") +1, Font.indexOf(",")));
						}
					}
				} catch (Exception e)
				{
					System.out.println(e);
				}

			}
		}

	}

}
