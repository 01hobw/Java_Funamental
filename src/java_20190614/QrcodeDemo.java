package java_20190614;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrcodeDemo {
	public boolean isCreareQrcode(String next) throws Exception{
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix qrCode = writer.encode(next, BarcodeFormat.QR_CODE, 300, 300);
		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(qrCode);

		File f = new File("c:\\down\\qrcode.png");
		//boolean isSuccess = ImageIO.write(qrImage, "PNG", f);
		return ImageIO.write(qrImage, "PNG", f);
	}
	
	public static void main(String[] args) throws WriterException, IOException {
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix qrCode = writer.encode("http;//www.naver.com", BarcodeFormat.QR_CODE, 300, 300);
		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(qrCode);

		File f = new File("c:\\down\\qrcode.png");
		boolean isSuccess = ImageIO.write(qrImage, "PNG", f);
		System.out.println(isSuccess);
	}
	// 바코드 만들기 CodaBarWriter writer = new CodaBarWriter();
}
