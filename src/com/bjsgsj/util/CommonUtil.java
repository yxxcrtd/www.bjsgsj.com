package com.bjsgsj.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.StringUtil;

/**
 * 通用辅助函数
 * 
 * @author Yang XinXin
 * @version 2.0.0, 2010-09-01 9:26:53
 */
public class CommonUtil {

	/**
	 * 空字符串
	 */
	public static final String EMPTY_STRING = "";
	
	private static int width;
	private static int height;
	private static int scaleWidth;
	private static double support = (double) 3.0;
	private static double PI = (double) 3.14159265358978;
	private static double[] contrib;
	private static double[] normContrib;
	private static double[] tmpContrib;
	private static int nDots;
	private static int nHalfDots;
	
	/**
	 * Default Constructor
	 */
	private CommonUtil() {
		// 
	}

	/**
	 * 生成高质量的缩略图
	 * 
	 * @param fromFileStr 原图片的地址
	 * @param saveToFileStr 生成缩略图的地址
	 * @param formatWideth 生成图片的宽度
	 * @param formatHeight 生成图片的高度
	 * @throws Exception 
	 */
	public static void generateHighQulityThumbnail(String fromFileStr, String saveToFileStr, int formatWideth, int formatHeight) throws Exception {
		BufferedImage srcImage;
		File saveFile = new File(saveToFileStr);
		File fromFile = new File(fromFileStr);
		srcImage = javax.imageio.ImageIO.read(fromFile); // construct image
		int imageWideth = srcImage.getWidth(null);
		int imageHeight = srcImage.getHeight(null);
		int changeToWideth = 0;
		int changeToHeight = 0;
		if (imageWideth > 0 && imageHeight > 0) {
			// flag = true;
			if (imageWideth / imageHeight >= formatWideth / formatHeight) {
				if (imageWideth > formatWideth) {
					changeToWideth = formatWideth;
					changeToHeight = (imageHeight * formatWideth) / imageWideth;
				} else {
					changeToWideth = imageWideth;
					changeToHeight = imageHeight;
				}
			} else {
				if (imageHeight > formatHeight) {
					changeToHeight = formatHeight;
					changeToWideth = (imageWideth * formatHeight) / imageHeight;
				} else {
					changeToWideth = imageWideth;
					changeToHeight = imageHeight;
				}
			}
		}
		srcImage = imageZoomOut(srcImage, changeToWideth, changeToHeight);
		ImageIO.write(srcImage, "JPEG", saveFile);
	}

	public static BufferedImage imageZoomOut(BufferedImage srcBufferImage, int w, int h) {
		width = srcBufferImage.getWidth();
		height = srcBufferImage.getHeight();
		scaleWidth = w;
		if (DetermineResultSize(w, h) == 1) {
			return srcBufferImage;
		}
		CalContrib();
		BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
		BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
		return pbFinalOut;
	}

	// 决定图像尺寸
	private static int DetermineResultSize(int w, int h) {
		double scaleH, scaleV;
		scaleH = (double) w / (double) width;
		scaleV = (double) h / (double) height;
		// 需要判断一下scaleH，scaleV，不做放大操作
		if (scaleH >= 1.0 && scaleV >= 1.0) {
			return 1;
		}
		return 0;
	}

	private static double Lanczos(int i, int inWidth, int outWidth, double Support) {
		double x;
		x = (double) i * (double) outWidth / (double) inWidth;
		return Math.sin(x * PI) / (x * PI) * Math.sin(x * PI / Support) / (x * PI / Support);
	}

	private static void CalContrib() {
		nHalfDots = (int) ((double) width * support / (double) scaleWidth);
		nDots = nHalfDots * 2 + 1;
		try {
			contrib = new double[nDots];
			normContrib = new double[nDots];
			tmpContrib = new double[nDots];
		} catch (Exception e) {
			System.out.println("init contrib, normContrib, tmpContrib" + e);
		}
		int center = nHalfDots;
		contrib[center] = 1.0;
		double weight = 0.0;
		int i = 0;
		for (i = 1; i <= center; i++) {
			contrib[center + i] = Lanczos(i, width, scaleWidth, support);
			weight += contrib[center + i];
		}
		for (i = center - 1; i >= 0; i--) {
			contrib[i] = contrib[center * 2 - i];
		}
		weight = weight * 2 + 1.0;
		for (i = 0; i <= center; i++) {
			normContrib[i] = contrib[i] / weight;
		}
		for (i = center + 1; i < nDots; i++) {
			normContrib[i] = normContrib[center * 2 - i];
		}
	}

	// 处理边缘
	private static void CalTempContrib(int start, int stop) {
		double weight = 0;
		int i = 0;
		for (i = start; i <= stop; i++) {
			weight += contrib[i];
		}
		for (i = start; i <= stop; i++) {
			tmpContrib[i] = contrib[i] / weight;
		}
	}

	private static int GetRedValue(int rgbValue) {
		int temp = rgbValue & 0x00ff0000;
		return temp >> 16;
	}

	private static int GetGreenValue(int rgbValue) {
		int temp = rgbValue & 0x0000ff00;
		return temp >> 8;
	}

	private static int GetBlueValue(int rgbValue) {
		return rgbValue & 0x000000ff;
	}

	private static int ComRGB(int redValue, int greenValue, int blueValue) {
		return (redValue << 16) + (greenValue << 8) + blueValue;
	}

	// 行水平滤波
	private static int HorizontalFilter(BufferedImage bufImg, int startX, int stopX, int start, int stop, int y, double[] pContrib) {
		double valueRed = 0.0;
		double valueGreen = 0.0;
		double valueBlue = 0.0;
		int valueRGB = 0;
		int i, j;
		for (i = startX, j = start; i <= stopX; i++, j++) {
			valueRGB = bufImg.getRGB(i, y);
			valueRed += GetRedValue(valueRGB) * pContrib[j];
			valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			valueBlue += GetBlueValue(valueRGB) * pContrib[j];
		}
		valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen), Clip((int) valueBlue));
		return valueRGB;

	}

	// 图片水平滤波
	private static BufferedImage HorizontalFiltering(BufferedImage bufImage, int iOutW) {
		int dwInW = bufImage.getWidth();
		int dwInH = bufImage.getHeight();
		int value = 0;
		BufferedImage pbOut = new BufferedImage(iOutW, dwInH, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < iOutW; x++) {
			int startX;
			int start;
			int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
			int y = 0;
			startX = X - nHalfDots;
			if (startX < 0) {
				startX = 0;
				start = nHalfDots - X;
			} else {
				start = 0;
			}
			int stop;
			int stopX = X + nHalfDots;
			if (stopX > (dwInW - 1)) {
				stopX = dwInW - 1;
				stop = nHalfDots + (dwInW - 1 - X);
			} else {
				stop = nHalfDots * 2;
			}
			if (start > 0 || stop < nDots - 1) {
				CalTempContrib(start, stop);
				for (y = 0; y < dwInH; y++) {
					value = HorizontalFilter(bufImage, startX, stopX, start, stop, y, tmpContrib);
					pbOut.setRGB(x, y, value);
				}
			} else {
				for (y = 0; y < dwInH; y++) {
					value = HorizontalFilter(bufImage, startX, stopX, start, stop, y, normContrib);
					pbOut.setRGB(x, y, value);
				}
			}
		}
		return pbOut;
	}

	private static int VerticalFilter(BufferedImage pbInImage, int startY, int stopY, int start, int stop, int x, double[] pContrib) {
		double valueRed = 0.0;
		double valueGreen = 0.0;
		double valueBlue = 0.0;
		int valueRGB = 0;
		int i, j;
		for (i = startY, j = start; i <= stopY; i++, j++) {
			valueRGB = pbInImage.getRGB(x, i);
			valueRed += GetRedValue(valueRGB) * pContrib[j];
			valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			valueBlue += GetBlueValue(valueRGB) * pContrib[j];
			// System.out.println(valueRed+"->" + Clip((int)valueRed) + "<-");
			// System.out.println(valueGreen+"->" + Clip((int)valueGreen) + "<-");
			// System.out.println(valueBlue+"->" + Clip((int)valueBlue) + "<-" + "-->");
		}
		valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen), Clip((int) valueBlue));
		// System.out.println(valueRGB);
		return valueRGB;
	}

	private static BufferedImage VerticalFiltering(BufferedImage pbImage, int iOutH) {
		int iW = pbImage.getWidth();
		int iH = pbImage.getHeight();
		int value = 0;
		BufferedImage pbOut = new BufferedImage(iW, iOutH, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < iOutH; y++) {
			int startY;
			int start;
			int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);
			startY = Y - nHalfDots;
			if (startY < 0) {
				startY = 0;
				start = nHalfDots - Y;
			} else {
				start = 0;
			}
			int stop;
			int stopY = Y + nHalfDots;
			if (stopY > (int) (iH - 1)) {
				stopY = iH - 1;
				stop = nHalfDots + (iH - 1 - Y);
			} else {
				stop = nHalfDots * 2;
			}
			if (start > 0 || stop < nDots - 1) {
				CalTempContrib(start, stop);
				for (int x = 0; x < iW; x++) {
					value = VerticalFilter(pbImage, startY, stopY, start, stop, x, tmpContrib);
					pbOut.setRGB(x, y, value);
				}
			} else {
				for (int x = 0; x < iW; x++) {
					value = VerticalFilter(pbImage, startY, stopY, start, stop, x, normContrib);
					pbOut.setRGB(x, y, value);
				}
			}
		}
		return pbOut;
	}
	
	private static int Clip(int x) {
		if (x < 0)
			return 0;
		if (x > 255)
			return 255;
		return x;
	}
	
	/**
	 * 生成纯静态的HTML文件
	 * 
	 * @param folderName 文件夹名称
	 * @param ftl FTL文件
	 * @param htmlName HTML文件名
	 * @param map Key-Value数据
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void generateHTML(String folderName, String ftl, String htmlName, Map<String, Object> map, ServletContext servletContext) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		// 如果将第二个参数设置为：null，则表示网站根目录（相对路径）
		cfg.setServletContextForTemplateLoading(servletContext, File.separator + folderName);
		cfg.setEncoding(Locale.getDefault(), "UTF-8"); // 防止乱码
		Template template = cfg.getTemplate(ftl);
		template.setEncoding("UTF-8");
		String path = servletContext.getRealPath("/");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + htmlName));
		File htmlFile = new File(path + htmlName);
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
		// map.put("JspTaglibs", new TaglibFactory(servletContext));
		template.process(map, out);
		bufferedWriter.close(); // 关闭缓冲流
		out.flush(); // 清除缓冲区
		out.close(); // 关闭输出流
	}
	
	
	
	
	
	/**
	 * 获取主机名
	 * 
	 * @return
	 */
	public static String getHostName() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		if (null != address) {
			return address.getHostName();
		} else {
			return "没有获取到！";
		}
	}
	
	/**
	 * 获取 MAC 地址
	 * 
	 * @return
	 */
	public static String getMacAddress() {
		String os = getOS();
		if (os.startsWith("Windows")) {
			return getWindowsMACAddress();
		} else if (os.startsWith("Linux")) {
			return getLinuxMACAddress();
		} else {
			return getUnixMacAddress();
		}
	}
	
	/**
	 * 获取 操作系统名称
	 * 
	 * @return
	 */
	public static String getOS() {
		return System.getProperty("os.name");
	}
	
	/**
	 * 获取 Unix网卡的 MAC地址。Unix下的命令，一般取eth0作为本地主网卡
	 * 
	 * @return
	 */
	private static String getUnixMacAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while (null != (line = bufferedReader.readLine())) {
				// 寻找字符串：hwaddr
				index = line.toLowerCase().indexOf("hwaddr");
				if (index != -1) {
					mac = line.substring(index + "hwaddr".length() + 1).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}

	/**
	 * 获取 Linux网卡的MAC地址。一般取eth0作为本地主网卡
	 * 
	 * @return
	 */
	private static String getLinuxMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while (null != (line = bufferedReader.readLine())) {
				index = line.toLowerCase().indexOf("硬件地址");
				if (index != -1) {
					mac = line.substring(index + 4).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}

	/**
	 * 获取 Widnows网卡的MAC地址
	 * 
	 * @return
	 */
	private static String getWindowsMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while (null != (line = bufferedReader.readLine())) {
				index = line.toLowerCase().indexOf("physical address");
				if (index != -1) {
					index = line.indexOf(":");
					if (index != -1) {
						mac = line.substring(index + 1).trim();
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}
	
	/**
	 * 安全的比较两个字符串（区分大小写）
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean stringEquals(String a, String b) {
		// null == null
		if (null == a && null == b)
			return true;
		// null != not null string
		if (null == a || null == b)
			return false;
		return a.equals(b);
	}
	
	/**
	 * 安全的比较两个字符串（不区分大小写）
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Boolean stringEqualsIgnoreCase(String a, String b) {
		// 都等于null，返回真
		if (null == a && null == b) {
			return true;
		}
		// （在上面条件的基础之上）任意一个为空，返回假
		if (null == a || null == b) {
			return false;
		}
		return a.equalsIgnoreCase(b);
	}
	
	
	
	
	
	


	/**
	 * 比较两个数字是否相等, 如果两个都等于 null 则认为相等
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static final Boolean equals(Integer i, Integer j) {
		if (null == i)
			return j == null;
		if (null == j)
			return false;
		return i.intValue() == j.intValue();
	}

	/**
	 * 对指定字符串进行 UTF-8 的 URL 编码
	 * 
	 * @param url
	 * @return
	 */
	public static String urlUtf8Encode(String url) {
		try {
			if (null == url || url.length() == 0)
				return "";
			return StringUtil.URLEnc(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	/**
	 * 判定指定的字符串是否为空, 如 null, '', '(全部为空格的字符串)'
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		if (null == str || str.length() == 0)
			return true;
		if (str.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 判定给定的名字是否是一个合法的名字，系统要求使用 英文、数字、下划线做为名字字符，第一个字符必须是英文
	 * 
	 * @param name
	 * @return true/false
	 */
	public static boolean isValidName(String name) {
		if (null == name)
			return false;

		if (name.length() == 0)
			return false;

		for (int i = 0; i < name.length(); ++i) {
			char ch = name.charAt(i);
			if (isEnglishChar(ch))
				continue;
			if (i > 0 && isDigitChar(ch))
				continue;
			if (i > 0 && ch == '_')
				continue;
			return false;
		}
		return true;
	}

	/**
	 * isValidName 是否的辅助函数
	 *
	 * @param ch
	 * @return
	 */
	public static final boolean isDigitChar(char ch) {
		return (ch <= '9' && ch >= '0');
	}

	/**
	 * isValidName 使用的辅助函数
	 *
	 * @param ch
	 * @return
	 */
	private static final boolean isEnglishChar(char ch) {
		return (ch <= 'Z' && ch >= 'A') || (ch <= 'z' && ch >= 'a');
	}

	/**
	 * 空的字符串数组
	 */
	public static final String[] EMPTY_STRING_ARRAY = new String[] {};

	/**
	 * 解析一个标签字符串，返回 String[]，标签字符串使用 ',' 等字符分隔
	 * 
	 * @param tagstr
	 * @return
	 */
	public static final String[] parseTagList(final String tagstr) {
		if (null == tagstr || tagstr.length() == 0)
			return EMPTY_STRING_ARRAY;
		List<String> tag_list = new ArrayList<String>();

		// sep_string 也许可以被配置在外面。下面的列表基本够用.
		String sep_string = ",;，。；|｜\r\n\t ";
		int start = 0, next = 0;
		while (next < tagstr.length()) {
			char ch = tagstr.charAt(next);
			if (sep_string.indexOf(ch) >= 0) {
				String tag_name = tagstr.substring(start, next).trim();
				start = next + 1;
				if (tag_name.length() > 0 && !tag_list.contains(tag_name))
					tag_list.add(tag_name);
			}
			++next;
		}

		if (start < next) {
			String tag_name = tagstr.substring(start).trim();
			if (tag_name.length() > 0 && !tag_list.contains(tag_name))
				tag_list.add(tag_name);
		}
		return (String[]) tag_list.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * 标准化表示一个标签字符串，使用 ',' 分隔所有标签
	 * 
	 * @param tags
	 * @return
	 * @remark GroupServiceImpl 使用其规范化标签字符串.
	 */
	public static final String standardTagsString(String[] tags) {
		return arrayConcat(tags, ",");
	}

	/**
	 * 标准化表示一个标签字符串
	 * 
	 * @param tagstr
	 * @return 去掉了空格、标签用标准 ',' 来分隔所有标签
	 */
	public static final String standardTagsString(String tagstr) {
		return standardTagsString(parseTagList(tagstr));
	}

	/**
	 * 判断一个 Integer 对象是否为 null 或者值等于 0
	 * 
	 * @param i
	 * @return true 表示为 null 或者值等于 0; false 表示不是
	 */
	public static final boolean isZeroOrNull(Integer i) {
		if (null == i)
			return true;
		return i.intValue() == 0;
	}

	/**
	 * 比较两个字符串是否相等, 如果两个都等于 null 则认为相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static final boolean equals(String a, String b) {
		if (null == a)
			return b == null;
		if (null == b)
			return false;
		return a.equals(b);
	}

	/**
	 * 创建一个新的 缩略图
	 *
	 * @param imgFileDir
	 * @param imgfile
	 * @param newWidth
	 * @param newHeight
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static boolean CreateNewThumbnail(String imgFileDir, String imgfile, Integer newWidth, Integer newHeight) throws Exception {
		String prefix = "s";
		double Ratio = 0.0;
		File F = new File(imgFileDir, imgfile);
		if (!F.isFile())
			throw new Exception(F + " 不是一个文件路径！");
		String FileName = imgfile.substring(imgfile.lastIndexOf("\\") + 1, imgfile.length());
		String fileExt = FileName.substring(FileName.lastIndexOf(".") + 1, FileName.length());
		File ThF = new File(imgFileDir, prefix + FileName);
		BufferedImage Bi = ImageIO.read(F);

		Image Itemp = Bi.getScaledInstance(newWidth, newHeight, Bi.SCALE_SMOOTH);
		
		// 等比例缩放
		if ((Bi.getHeight() > newHeight) || (Bi.getWidth() > newWidth)) {
			if (Bi.getHeight() > Bi.getWidth())
				Ratio = (double) newHeight / Bi.getHeight();
			else
				Ratio = (double) newWidth / Bi.getWidth();
		}

		AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);
		Itemp = op.filter(Bi, null);
		try {
			ImageIO.write((BufferedImage) Itemp, fileExt, ThF);
		} catch (Exception e) {
			throw new Exception(" ImageIo.write error in CreateThumbnail : " + e.getLocalizedMessage());
		}
		return true;
	}

	/**
	 * 实现 JavaScript 的 escape
	 *
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 实现 JavaScript 的 unescape
	 *
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src .substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * XML Encode
	 *
	 * @param strXml
	 * @return
	 */
	public static String xmlEncode(String strXml) {
		if (null == strXml)
			return "";
		return freemarker.template.utility.StringUtil.XMLEnc(strXml);
	}

	/**
	 * 
	 *
	 * @param status
	 * @param desc
	 * @return
	 */
	public static String responseXml(StatusCode status, String desc) {
		return ResponseXml(status, desc, "", "");
	}

	/**
	 * 
	 *
	 * @param status
	 * @param desc
	 * @param returnurl
	 * @return
	 */
	public static String ResponseXml(StatusCode status, String desc, String returnurl) {
		return ResponseXml(status, desc, returnurl, "");
	}

	/**
	 * 
	 *
	 * @param status
	 * @param desc
	 * @param returnurl
	 * @param retdata
	 * @return
	 */
	public static String ResponseXml(StatusCode status, String desc, String returnurl, String retdata) {
		return "<response>" + "<status>" + status + "</status>"
				+ "<description>" + xmlEncode(desc) + "</description>"
				+ "<redirecturl>" + xmlEncode(returnurl) + "</redirecturl>"
				+ "<data>" + xmlEncode(retdata) + "</data>" + "</response>";
	}

	/**
	 * 
	 *
	 * @author Yang Xinxin
	 * @version 1.0.0 Oct 20, 2009 3:59:23 PM
	 */
	public static enum StatusCode {
		OK, ERROR, NONE;
	}

	/**
	 * 将一个字符串数组使用 concator 拼接起来
	 * 
	 * @param strs 字符串数组
	 * @param concator 拼接字符串, 如 ','
	 * @return 例如 strs = ["Hello", "World!"], concator = "," 返回为 "Hello,World!"
	 */
	public static final String arrayConcat(String[] strs, String concator) {
		if (null == strs || strs.length == 0)
			return "";
		if (concator == null)
			concator = "";
		if (strs.length == 1)
			return strs[0];
		if (strs.length == 2)
			return strs[0] + concator + strs[1];
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(strs[0]);
		for (int i = 1; i < strs.length; ++i)
			strbuf.append(concator).append(strs[i]);
		return strbuf.toString();
	}

	/**
	 * 对指定字符串执行 HTML 编码
	 *
	 * @param s
	 * @return
	 */
	public static final String htmlEncode(String s) {
		if (null == s || s.length() == 0)
			return "";
		return freemarker.template.utility.StringUtil.HTMLEnc(s);
		
		/*
		 * int ln = s.length(); for (int i = 0; i < ln; i++) { char c =
		 * s.charAt(i); if (c == '<' || c == '>' || c == '&' || c == '"') {
		 * StringBuffer b = new StringBuffer(s.substring(0, i)); switch (c) {
		 * case '<': b.append("&lt;"); break; case '>': b.append("&gt;");
		 * break; case '&': b.append("&amp;"); break; case '"':
		 * b.append("&quot;"); break; } i++; int next = i; while (i < ln) { c =
		 * s.charAt(i); if (c == '<' || c == '>' || c == '&' || c == '"') {
		 * b.append(s.substring(next, i)); switch (c) { case '<':
		 * b.append("&lt;"); break; case '>': b.append("&gt;"); break; case '&':
		 * b.append("&amp;"); break; case '"': b.append("&quot;"); break; } next =
		 * i + 1; } i++; } if (next < ln) b.append(s.substring(next)); s =
		 * b.toString(); break; } // if c == } // for return s;
		 */
	}

	/**
	 * 得到不包含HTML代码的字符串
	 * 
	 * @param source
	 * @return
	 */
	public static final String eraseHtml(String source) {
		if (null == source || source.trim().length() <= 0)
			return source;
		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(source);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(sb, group);
			} else {
				matcher.appendReplacement(sb, " ");
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 将一组标识符转换为 SQL IN 子句的格式
	 * 
	 * @param ids
	 * @return 例如 ids = [1, 2, 3] 则返回为 '(1, 2, 3)'
	 * @throws IllegalArgumentException 如果 ids == null 或者 ids.length == 0
	 */
	public static final String toSqlInString(int[] ids) {
		if (null == ids || ids.length == 0)
			throw new IllegalArgumentException("ids");

		StringBuffer strbuf = new StringBuffer(ids.length * 6);
		strbuf.append("(").append(ids[0]);
		for (int i = 1; i < ids.length; ++i)
			strbuf.append(",").append(ids[i]);
		strbuf.append(")");
		return strbuf.toString();
	}

	/**
	 * 将一组标识符转换为 SQL IN 子句的格式
	 * 
	 * @param ids
	 * @return 例如 ids = [1, 2, 3] 则返回为 '(1, 2, 3)'
	 * @throws IllegalArgumentException 如果 ids == null 或者 ids.length == 0
	 */
	public static final String toSqlInString(List<Integer> ids) {
		if (null == ids || ids.size() == 0)
			throw new IllegalArgumentException("ids");

		StringBuffer strbuf = new StringBuffer(ids.size() * 6);
		strbuf.append("(").append(ids.get(0));
		for (int i = 1; i < ids.size(); ++i)
			strbuf.append(",").append(ids.get(i));
		strbuf.append(")");
		return strbuf.toString();
	}

	/**
	 * 将指定文件内容写入指定正文文件，使用指定的编码，我们建议使用 UTF-8
	 * 
	 * @param fileName
	 * @param content
	 * @param charsetName 字符集，参见 OutputStreamWriter()
	 */
	public static final void saveFile(String fileName, String content, String charsetName) {
		FileOutputStream oStream = null;
		try {
			File file = new File(fileName);
			oStream = new FileOutputStream(file);
			java.io.Writer out = new java.io.OutputStreamWriter(oStream, charsetName);
			out.write(content);
			out.flush();
			out.close();
		} catch (IOException ex) {
			throw new RuntimeException("不能存储文件：" + fileName, ex);
		} finally {
			if (null != oStream) {
				try {
					oStream.close();
				} catch (Exception ex) {
					// Ignore
				}
			}
		}
	}

	/**
	 * 从指定文件读取入正文，使用指定的编码。我们建议使用 UTF-8
	 * 
	 * @param fileName
	 * @param charsetName
	 * @return
	 */
	public static final String readFile(String fileName, String charsetName) {
		FileInputStream stream = null;
		try {
			stream = new java.io.FileInputStream(fileName);
			InputStreamReader reader = new InputStreamReader(stream, charsetName);
			StringBuilder strbuf = new StringBuilder();
			char[] cbuf = new char[4096];
			while (true) {
				int len = reader.read(cbuf);
				if (len < 0)
					break;
				strbuf.append(cbuf, 0, len);
			}
			return strbuf.toString();
		} catch (IOException ex) {
			return null;
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (Exception e) {
					// Ignore
				}
			}
		}
	}

	/**
	 * 得到指定文件的后缀
	 * 
	 * @param fileName
	 * @return 如果没有后缀则返回 "", 否则返回后缀(不含 . 符号)
	 */
	public static final String getFileExtension(String fileName) {
		if (null == fileName || fileName.length() == 0)
			return "";
		int pos = fileName.lastIndexOf('.');
		if (pos < 0)
			return "";
		String ext = fileName.substring(pos + 1);
		if (ext.indexOf('/') >= 0 || ext.indexOf('\\') >= 0)
			return "";
		return ext;
	}

	/**
	 * 得到一个 href 中的文件名部分, 一般是最后一个 '/', '\\' 后面的部分
	 * 
	 * @param href
	 * @return
	 */
	public static final String getFileNameFromHref(String href) {
		if (null == href || href.length() == 0)
			return "";
		int index = href.lastIndexOf('/');
		if (index >= 0)
			href = href.substring(index + 1);
		index = href.lastIndexOf('\\');
		if (index >= 0)
			href = href.substring(index + 1);
		return href;
	}

	/**
	 * 根据协作组标识计算出'协作组资源分类'中使用的 type 字符串, = 'group_' + $groupId
	 * 
	 * @param groupId
	 * @return
	 */
	public static final String toGroupCategoryType(int groupId) {
		return "group_" + groupId;
	}

	/**
	 * 根据用户标识计算出用户'个人文章分类'中使用的 type 字符串, = 'user_' + $userId
	 * 
	 * @param userId
	 * @return
	 */
	public static final String toUserArticleCategoryType(int userId) {
		return "user_" + userId;
	}

	/**
	 * 根据用户标识计算用户'个人资源分类'的 type, 其 = 'user_res_' + $userId
	 * 
	 * @param userId
	 * @return 'user_res_' + $userId
	 */
	public static final String toUserResourceCategoryType(int userId) {
		return "user_res_" + userId;
	}

	/**
	 * 将 Hibernate 返回的 Long, Integer 转换为 Integer, Hibernate 有的版本返回的 COUNT(*) 为 Long 型
	 * 
	 * @param result
	 * @return
	 */
	public static final Integer safeXtransHiberInteger(Object result) {
		if (null == result)
			return null;
		if (result instanceof Integer)
			return (Integer) result;
		if (result instanceof Long)
			return ((Long) result).intValue();
		if (result instanceof Number)
			return ((Number) result).intValue();
		return null;
	}

	/**
	 * siteUrl - 'http://www.domain.com/', 'http://domain/ctxt/' must endwith '/'
	 *
	 * @param siteUrl
	 * @return
	 */
	public static final String hostUrl(String siteUrl) {
		int pos1 = siteUrl.indexOf("://");
		if (pos1 < 0)
			return siteUrl;
		int pos2 = siteUrl.indexOf("/", pos1 + 3);
		if (pos2 < 0)
			return siteUrl;
		return siteUrl.substring(0, pos2 + 1);
	}

	/**
	 * 按照 RSS GMT 日期格式输出一个日期的字符串
	 * 
	 * @param date
	 * @return 结果示例: 'Wed,07 Jun 2006 01:54:50 GMT'
	 */
	public static String rssDateFormat(Date date) {
		// 得到服务器时区偏差，然后用该时区偏差构造一个 GMT TimeZone
		int timeZoneOffset = getTimeZoneOffset();
		SimpleTimeZone zone = new SimpleTimeZone(timeZoneOffset, "GMT");

		// 构造日期格式化字符串
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
		formatter.setTimeZone(zone);

		return formatter.format(date);
	}

	/**
	 * 获得当前服务器缺省的时区偏差, 以小时为单位. 例如中国返回 +8
	 * 
	 * @return
	 */
	public static int getTimeZoneOffset() {
		TimeZone deft = TimeZone.getDefault();
		if (null == deft)
			return 8;
		int raw_ofs = deft.getRawOffset(); // 以毫秒为单位

		return raw_ofs / (60 * 60 * 1000);
	}
	
}
