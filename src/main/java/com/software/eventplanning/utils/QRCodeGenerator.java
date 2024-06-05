package com.software.eventplanning.utils;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
public class QRCodeGenerator {

    /**
     * 生成二维码图片并保存到指定路径。
     * @param content 二维码携带的信息
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param filePath 图片保存路径
     */
    public static void generateAndSave(String content, int width, int height, String filePath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR Code saved to: " + filePath);
        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR Code: " + e.getClass());
        }
    }

    /**
     * 生成二维码图片并返回字节数组，便于在网络上传输。
     * @param content 二维码内容
     * @param width 宽度
     * @param height 高度
     * @return 二维码图片的字节数组
     */
    public static byte[] generateAsByteArray(String content, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageConfig config = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, config);
            return pngOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code.", e);
        }
    }
}