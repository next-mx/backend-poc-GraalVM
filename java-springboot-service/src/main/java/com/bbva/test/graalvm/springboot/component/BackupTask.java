package com.bbva.test.graalvm.springboot.component;

import com.bbva.test.graalvm.springboot.dao.ComentarioCustomRepo;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class BackupTask {
	private boolean ban = false;
	private String nameFile = "";

	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private ComentarioCustomRepo comentarioCustomRepo;

	@Scheduled(fixedRate = 1000)
	private void makeBackup() {
		try {
			if (ban) {
				ban = false;
				makeBackupToFile();
			}
		} catch (InterruptedException e) {
			ban = false;
		}
	}

	private void makeBackupToFile() throws InterruptedException {
		Thread.sleep(5000);
		obtainPath();
		List<MovieDTO> allMovies = this.movieRepo.findAll();
		int indice = 0;
		try {
			Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.nameFile)));
			for (MovieDTO movie : allMovies) {
				String cad = obtainCadena(movie, indice, allMovies.size());
				System.out.println(cad);
				fileWriter.write(cad);
				fileWriter.write("\n");
				indice++;
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {

		}
	}

	private String obtainCadena(MovieDTO movie, int indice, int totalMovies) {
		ObjectMapper Obj = new ObjectMapper();
		String json = "" + getobjectIdFile(indice, this.comentarioCustomRepo.totalCommnetByMovie(movie.get_id()), totalMovies);
		try {
			json += ",".concat(Obj.writeValueAsString(movie));
		} catch (IOException e) {

		}
		return json;
	}

	private void obtainPath() {
		if (nameFile.equals("")) {
			String aqui = new File(".").getAbsolutePath();
			this.nameFile = aqui.substring(0, aqui.lastIndexOf('.')).concat("movies_catalog.csv");
		}
	}

	public long getobjectIdFile(int id, int numberComment, int totalMovies) {
		LocalDateTime dateTime = LocalDateTime.now();
		return ((dateTime.toEpochSecond(ZoneOffset.MAX) + id) * numberComment) / totalMovies;
	}


	public void makeBackup(boolean make) {
		this.ban = make;
	}
}
