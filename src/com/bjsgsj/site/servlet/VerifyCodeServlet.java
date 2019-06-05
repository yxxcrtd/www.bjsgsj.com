package com.bjsgsj.site.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 生成验证码
 * 
 * @author Yang XinXin
 * @version 2.0.0, 2011-07-10 12:59:29
 */
public class VerifyCodeServlet extends HttpServlet {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6427451309961578724L;
	
	/**
	 * 设置字体和大小
	 */
	private Font mFont = new Font("Arial Black", Font.BOLD, 18);

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * 生成随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 阻止生成的页面内容被缓存，保证每次重新生成随机验证码
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 验证码的宽度和高度
		int width = 75;
		int height = 20;

		// 生成一张新图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 在图片中绘制内容
		Graphics g = image.getGraphics();
		Random random = new Random();
		
		// 设置图片的背景颜色
		g.setColor(getRandColor(150, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		
		// 画边框
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		
		// 将验证码显示到图象中
		g.setFont(mFont);

		// 随机生成线条，让图片看起来更加杂乱
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 随机生成线条，让图片看起来更加杂乱
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		// 定义一个用于保存系统生成的随机字符串变量
		String sRandom = "";

		// 返回一个 4 位的验证码
		for (int i = 0; i < 4; i++) {
			// 取得一个随机字符
			String tmp = getRandomChar(1);
			//String tmp = RandomStringUtils.randomAlphanumeric(1);
			sRandom += tmp;
			// 将系统生成的随机字符添加到验证码的图片上
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(tmp, 15 * i + 10, 15);
		}

		// 取得用户 Session
		HttpSession session = request.getSession(true);

		// 将系统生成的图形验证码添加到用户 Session 中
		session.setAttribute("random", sRandom);
		g.dispose();

		// 输出图形验证码图片
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 生成随机数
	 * 
	 * @param length
	 * @return
	 */
	private static String getRandomChar(int length) {
		String str = "abcdefhjkmnpstuvwxyzABCDEFHJKMNPSTUVWXYZ2345678";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(47);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

}

