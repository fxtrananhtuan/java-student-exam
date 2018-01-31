/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MesutOezil
 */
public class MBDownLoadFile implements Serializable {

    /** Creates a new instance of MBDownLoadFile */
   

    @PostConstruct
    public void init() throws FileNotFoundException, IOException {
        String excute= SessionTool.getSession("path").toString();
        String name=SessionTool.getSession("name").toString();
        String addName="";
        if(SessionTool.getSession("sub")!=null){
            addName=SessionTool.getSession("sub").toString()+name;
        }
        else{
            addName=name;
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();
        // response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + addName + "\"");
        String path = SessionTool.getLineExecutePath(excute);
        String realPath = SessionTool.getRealPath(path);
        realPath += "/"+name;
        System.out.println(realPath);
        File f = new File(realPath);
        FileInputStream fis = new FileInputStream(f);
        byte[] buf = new byte[1024];
        try {
            long length = f.length();
            BufferedInputStream in = new BufferedInputStream(fis);
            ServletOutputStream out = response.getOutputStream();
            response.setContentLength((int) length);
            while ((in != null) && ((length = in.read(buf)) != -1)) {
                out.write(buf, 0, (int) length);
            }
            in.close();
            out.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        fc.responseComplete();
    }

    public MBDownLoadFile() {
    }

    public String toDownload(){
        return "";
    }
}
