package com.example.Controller;

import com.example.Repo.QRCodeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.OutputStream;

@Controller
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @RequestMapping("/")
    public String index(){
        return"index";
    }

    @PostMapping("/showQRCode")
    public String showQRCode(String qrContent , Model model){
        model.addAttribute("qrCodeContent","/generateQRCode?qrContent=" + qrContent);
        return "show-qr-code";
    }


    @GetMapping("/generateQRCode")
    public void generateQRCode(String qrContent, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        byte[] qrCode = qrCodeService.generateQRCode(qrContent,500,500);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCode);
    }

    @GetMapping("/qr")
        public ResponseEntity<byte[]> getQrCode(){
        String contenToGenerateQRCode = "Happy Coding.... Thank You....";
        byte[] qrCode = qrCodeService.generateQRCode(contenToGenerateQRCode,500,500);
        System.out.println("Your QR-Code is Generated Successfully");
        return  ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
    }

}
