package com.baidu.ueditor;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

public class ImgCompress {
	private Image img;
	private BufferedImage image;
	private int width;
	private int height;

	/*
	构造函数
	*/
	public ImgCompress(File file) throws IOException
	{
		img = ImageIO.read(file);
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	/** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
	public void resizeFix(int w, int h) throws IOException
	{
		if( (width/height) > (w/h) ) resizeByWidth(w);
		else resizeByHeight(h);
	}
	/** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */
	public void resizeByWidth(int w)
	{
		int h = (int)(height*w/width);
		resize(w, h);
	}
	/** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
	public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h);  
    }
	/** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    public void resize(int w, int h){
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
    }
    /**  
     *  
     * @param srcFilePath 图片所在的文件夹路径  
     * @param destFilePath 存放路径  
     * @param fileName 图片名  
     * @param w 目标宽  
     * @param h 目标高  
     * @param per 百分比  
     * @param is_cover 是否覆盖
     */   
    public void saveAsJPEG(
    		String filePath,
    		String fileName,
    		String fileType
    		)
    {
    	int w = 800;
		if (width>w) resizeByWidth(w);
		String destFilePath = filePath+"\\"+fileName+"."+fileType;
		File destFile = new File(destFilePath);
        FileOutputStream out;
		try {
			out = new FileOutputStream(destFile);
			ImageWriter iw = ImageIO.getImageWritersBySuffix(fileType).next();
	        ImageOutputStream ios = ImageIO.createImageOutputStream(out);
	        iw.setOutput(ios);
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
