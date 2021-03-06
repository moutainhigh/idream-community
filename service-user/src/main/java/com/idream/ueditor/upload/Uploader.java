package com.idream.ueditor.upload;

import com.idream.ueditor.define.State;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Uploader {

    @Autowired
    private OssBinaryUploader ossBinaryUploader;

    private HttpServletRequest request = null;
    private Map<String, Object> conf = null;

    public Uploader(HttpServletRequest request, Map<String, Object> conf) {
        this.request = request;
        this.conf = conf;
    }

    public final State doExec() {
        String filedName = (String) this.conf.get("fieldName");
        State state = null;

        if ("true".equals(this.conf.get("isBase64"))) {
            state = Base64Uploader.save(this.request.getParameter(filedName),
                    this.conf);
        } else {
//            state = new BinaryUploader().save(this.request, this.conf);
            state = new OssBinaryUploader().save(this.request, this.conf);
        }

        return state;
    }
}
