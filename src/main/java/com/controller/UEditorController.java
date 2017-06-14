package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/ueditor")
public class UEditorController {

	@RequestMapping(value="/config")
	public void config(HttpServletRequest request, HttpServletResponse response) {
		 
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
 
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
	@RequestMapping(value="/config", params={"action=uploadimage"}, method=RequestMethod.POST)
	@ResponseBody
	public Object uploadimage(HttpServletRequest request,HttpServletResponse response){
		return upload(request,response);
	}
	
	@RequestMapping(value="/config", params={"action=uploadfile"}, method=RequestMethod.POST)
	@ResponseBody
	public Object uploadfile(HttpServletRequest request,HttpServletResponse response){
		return upload(request,response);
	}
	
	public Object upload(HttpServletRequest request,HttpServletResponse response){
		String WebFilePath = null, myFileName = null, hashName = null, hashCode = null;
		String state = "FAIL";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date ndate = new Date();
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		cmr.setDefaultEncoding("utf-8");
		if( cmr.isMultipart(request) ){
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			MultipartFile file = mhsr.getFile("upfile");
			if(file != null){
				myFileName = file.getOriginalFilename();
				String folderName = request.getSession().getServletContext().getRealPath("/") + "Uploads_UEditor/" + format.format(ndate.getTime()) + "/";
				File folder = new File(folderName);
				if (!folder.exists() && !folder.isDirectory()) folder.mkdirs();
				Random ne = new Random();
				int RN = ne.nextInt(9999-1000+1)+1000;
				String FNWT = myFileName.substring(0, myFileName.lastIndexOf("."));
				String FileType = myFileName.substring(myFileName.lastIndexOf(".")+1, myFileName.length());
				String tmp = FNWT + String.valueOf(RN);
				hashCode = String.valueOf( Math.abs( tmp.hashCode() ) );
				hashName = hashCode + "." + FileType;
//				System.out.println(request.getRequestURI());							/DHProjectApp/ueditor/config
//				System.out.println(request.getRequestURL().toString());					http://localhost/DHProjectApp/ueditor/config
//				System.out.println(request.getServletContext().getContextPath());		/DHProjectApp
				
				String FileName = folderName + hashName;
				File imageFile = new File(FileName);
				
				FileInputStream inStream = null;
				FileOutputStream outStream = null;
				WriteRender wr = null;
				try {
					file.transferTo(imageFile);
					File in = imageFile;
					hashName = "c_" + hashName;
					FileName = folderName + hashName;
					File out = new File(FileName);
					ScaleParameter scaleParam = new ScaleParameter(800, 600);

					inStream = new FileInputStream(in);
					outStream = new FileOutputStream(out);
					ImageRender rr = new ReadRender(inStream);
					ImageRender sr = new ScaleRender(rr, scaleParam);
					wr = new WriteRender(sr, outStream);
					
					wr.render();
					state = "SUCCESS";
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					IOUtils.closeQuietly(inStream);
					IOUtils.closeQuietly(outStream);
					if (wr != null){
						try {
							wr.dispose();
						} catch (SimpleImageException e) {
							e.printStackTrace();
						}
					}
//					imageFile.delete();
				}
			}
		}
		StringBuffer url = request.getRequestURL();
		WebFilePath = url.delete( url.length() - request.getRequestURI().length() , url.length())
				.append(request.getServletContext().getContextPath()).append("/Uploads_UEditor/").toString();
		WebFilePath += format.format(ndate.getTime()) + "/" + hashName;		//附件从网页上打开的路径
		return "{\"original\":\""+myFileName+"\",\"url\":\""+WebFilePath+"\",\"title\":\""+myFileName+"\",\"state\":\""+state+"\"}";
	}
	
}
