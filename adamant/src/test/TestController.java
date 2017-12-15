package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestController {

	/**
	 * 启用浏览器
	 * @author xman
	 * @time 2016年11月24日 下午2:57:03
	 */
	public static void showBrowser1() {
		try {
			String url = "http://www.baidu.com";
			// String url = "http://www.hao123.com";
			java.net.URI uri = java.net.URI.create(url);
			// 获取当前系统桌面扩展
			java.awt.Desktop dp = java.awt.Desktop.getDesktop();
			// 判断系统桌面是否支持要执行的功能
			if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
				// File file = new File("D:\\aa.txt");
				// dp.edit(file);// 编辑文件
				dp.browse(uri);// 获取系统默认浏览器打开链接
				// dp.open(file);// 用默认方式打开文件
				// dp.print(file);// 用打印机打印文件
			}
		} catch (java.lang.NullPointerException e) {
			// 此为uri为空时抛出异常
			e.printStackTrace();
		} catch (Exception e) {
			// 此为无法获取系统默认浏览器
			e.printStackTrace();
		}
	}

	/**
	 * 启用浏览器
	 * @author xman
	 * @time 2016年11月24日 下午2:57:26
	 */
	public static void showBrowser2() {
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.baidu.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Java操作鼠标和键盘
	 * @author xman
	 * @time 2016年11月24日 下午3:38:13
	 * @throws Exception
	 */
	public static void useMouseAndKeyboard() throws Exception {
		Robot robot = new Robot();
		// 设置Robot产生一个动作后的休眠时间,否则执行过快
		robot.setAutoDelay(5000);// 5秒

		/*// 获取屏幕分辨率
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		Rectangle screenRect = new Rectangle(d);
		// 截图
		BufferedImage bufferedImage = robot.createScreenCapture(screenRect);
		// 保存截图
		File file = new File("screenRect.png");
		ImageIO.write(bufferedImage, "png", file);*/

		// 移动鼠标
		robot.mouseMove(500, 500);

		/*// 点击鼠标
		// 鼠标左键
		System.out.println("单击");
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		// 鼠标右键
		System.out.println("右击");
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);

		// 按下ESC，退出右键状态
		System.out.println("按下ESC");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		// 滚动鼠标滚轴
		System.out.println("滚轴");
		robot.mouseWheel(5);

		// 按下Alt+TAB键
		robot.keyPress(KeyEvent.VK_ALT);
		for (int i = 1; i <= 2; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
		robot.keyRelease(KeyEvent.VK_ALT);*/
		
		//ctrl+v
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		robot.setAutoDelay(3000);
		//回车
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void main(String[] args) {
		TestController.showBrowser1();
		try {
			TestController.useMouseAndKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch blockjava
			e.printStackTrace();
		}
	}

}
