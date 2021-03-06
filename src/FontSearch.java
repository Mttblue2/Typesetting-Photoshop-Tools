import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.aspose.psd.Image;
import com.aspose.psd.fileformats.psd.PsdImage;
import com.aspose.psd.fileformats.psd.layers.TextLayer;

public class FontSearch
{

	public static void main(String[] args) throws FontFormatException, IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("1: get PSD fonts \n2: get installed fonts");
		String action = keyboard.next();	
		
		if (action.equals("1"))
			getPSDFonts();
		else if (action.equals("2"))
			getInstalledFonts();

		keyboard.close();
	}

	public static void getPSDFonts()
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

	public static void getInstalledFonts()
	{
		String homePath = System.getProperty("user.home");
		String fontDirectory = homePath + "\\AppData\\Local\\Microsoft\\Windows\\Fonts";

		File fontScanner = new File(fontDirectory);

		File[] fontList = fontScanner.listFiles();
		ArrayList<Font> fonts = new ArrayList<Font>();

		for (int x = 0; x < fontList.length; x++)
		{
			if (!fontList[x].isDirectory())
			{
				try
				{
					fonts.add(Font.createFont(Font.TRUETYPE_FONT, fontList[x]));
				} catch (Exception e)
				{
					int y = fontList[x].toString().lastIndexOf("\\");
					System.out.println("Font " + fontList[x].toString().substring(y + 1) + " had an error.");
				}
			}
		}
		System.out.println("");

		Collections.sort(fonts, new fontCompare());

		for (int x = 0; x < fonts.size(); x++)
		{
			System.out.println(fonts.get(x).getFontName());
		}
	}
}

class fontCompare implements Comparator<Font>
{
	public int compare(Font a, Font b)
	{
		return a.getFontName().toLowerCase().compareTo(b.getFontName().toLowerCase());
	}
}
