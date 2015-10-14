package personal.deon.framework.fuliao.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageUtil {
	/**
	 * @param path 被裁剪图片的原始绝对路径
	 * @param w 裁剪后的宽度
	 * @param h 裁剪后的高度
	 * @param suffix 裁剪后的图片路径追加的后缀名，如‘L’表示裁剪的大图片，‘S’表示中图，‘M’表示小图
	 * @param waterText 裁剪后图片添加的水印文字
	 * @param drop 裁剪成功是否删除原图片，true删除，false不删除
	 * @return 返回裁剪后图片的绝对路径
	 */
	public static String cutImage(String path,int w,int h,char suffix,String waterText,boolean drop){
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			int width = image.getWidth();
			int height = image.getHeight();
			float scaleW = width/(0.0f+w);
			float scaleH = height/(0.0f+h);
			float sclae = scaleW>=scaleH ?scaleH:scaleW;
			int newW = (int)(w*sclae);
			int newH = (int)(h*sclae);
			int x = (width-newW)/2;
			int y = (height-newH)/2;
			
			BufferedImage tmp = image.getSubimage(x, y, newW, newH);
			Image img = tmp.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
			BufferedImage target = new BufferedImage(w, h,image.getType());
			Graphics2D g = target.createGraphics();
			g.drawImage(img, 0, 0, null);
			if(null != waterText){
				g.setColor(Color.BLACK);
				g.setFont(new Font(null, Font.CENTER_BASELINE, 4));
				g.drawString(waterText, 2, h-6);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
			}
			g.dispose();
			String imageType = path.substring(path.lastIndexOf(".")+1);
			String newFile = path.substring(0,path.lastIndexOf("."))+suffix+"."+imageType;
			ImageIO.write(target, imageType, new File(newFile));
			if(drop)
				file.delete();
			return file.getName().substring(0,file.getName().lastIndexOf("."))+suffix+"."+imageType;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 缩放图片
	 * @param path 图片理解
	 * @param w 图片宽度
	 * @param h 图片高度
	 * @param fontsize 文字大小
	 * @param suffix 文件后缀
	 * @param waterText 水印文字
	 * @param drop 是否删除原文件
	 * @return 新文件名称
	 */
	public static String scale(String path,int w,int h,int fontsize,char suffix,String waterText,boolean drop){
		try{
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			Image img = image.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
			BufferedImage target = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
			Graphics2D g = target.createGraphics();
			g.drawImage(img, 0, 0, null);
			if(null != waterText){
				g.setColor(Color.BLACK);
				g.setFont(new Font(null, Font.CENTER_BASELINE, fontsize));
				 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                         RenderingHints.VALUE_ANTIALIAS_ON);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
				g.drawString(waterText, w-getLength(waterText)*fontsize-15, h-6);
			}
			g.dispose();
			String imageType = path.substring(path.lastIndexOf(".")+1);
			String newFile = path.substring(0,path.lastIndexOf("."))+suffix+"."+imageType;
			ImageIO.write(target, imageType, new File(newFile));
			
			if(drop)
				file.delete();
			return file.getName().substring(0,file.getName().lastIndexOf("."))+suffix+"."+imageType;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	*/
	public static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}
	/***
	 * 为图片添加水印文字
	 * @param path
	 * @param waterText
	 * @param fontsize
	 */
	public static void waterText(String path,String waterText,int fontsize){
		try{
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			int w = image.getWidth();
			int h = image.getHeight();
			Graphics2D g = image.createGraphics();
			if(null != waterText){
				g.setColor(Color.BLACK);
				g.setFont(new Font(null, Font.CENTER_BASELINE, fontsize));
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
						RenderingHints.VALUE_ANTIALIAS_ON);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
				g.drawString(waterText, w-getLength(waterText)*fontsize-15, h-6);
			}
			g.dispose();
			String imageType = path.substring(path.lastIndexOf(".")+1);
			ImageIO.write(image, imageType, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
