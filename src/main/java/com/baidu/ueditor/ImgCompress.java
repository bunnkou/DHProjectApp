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
	���캯��
	*/
	public ImgCompress(File file) throws IOException
	{
		img = ImageIO.read(file);
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	/** 
     * ���տ�Ȼ��Ǹ߶Ƚ���ѹ�� 
     * @param w int ����� 
     * @param h int ���߶� 
     */  
	public void resizeFix(int w, int h) throws IOException
	{
		if( (width/height) > (w/h) ) resizeByWidth(w);
		else resizeByHeight(h);
	}
	/** 
     * �Կ��Ϊ��׼���ȱ�������ͼƬ 
     * @param w int �¿�� 
     */
	public void resizeByWidth(int w)
	{
		int h = (int)(height*w/width);
		resize(w, h);
	}
	/** 
     * �Ը߶�Ϊ��׼���ȱ�������ͼƬ 
     * @param h int �¸߶� 
     */  
	public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h);  
    }
	/** 
     * ǿ��ѹ��/�Ŵ�ͼƬ���̶��Ĵ�С 
     * @param w int �¿�� 
     * @param h int �¸߶� 
     */  
    public void resize(int w, int h){
        // SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���  
        image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // ������С���ͼ  
    }
    /**  
     *  
     * @param srcFilePath ͼƬ���ڵ��ļ���·��  
     * @param destFilePath ���·��  
     * @param fileName ͼƬ��  
     * @param w Ŀ���  
     * @param h Ŀ���  
     * @param per �ٷֱ�  
     * @param is_cover �Ƿ񸲸�
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
