package jp.co.sss.onepiececardviewer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImagePathService {
	
	public ResponseEntity<Resource> loadImageAsResponse(String image) {
		
		// クラスパスからの相対パスで読み込む
		Resource resource = new ClassPathResource(image);
		
		// ファイルの存在と読み込み権限の確認
		if (!resource.exists() || !resource.isReadable()) {
			throw new RuntimeException("画像ファイルが存在しないか読み取り不可");
		}
		
		// ファイルのContent-Typeを判定
		String contentType;
		try {
			contentType = Files.probeContentType(Paths.get(resource.getURI()));
		} catch (IOException e) {
			contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"inline; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
