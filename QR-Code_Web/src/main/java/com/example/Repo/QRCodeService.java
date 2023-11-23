package com.example.Repo;

public interface QRCodeService {
    byte[] generateQRCode(String qrContent, int width, int height);
}
