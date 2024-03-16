package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.repository.MovieRepositoryI;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;
import java.net.URL;

@Service
public class MovieService {
    @Autowired
    private MovieRepositoryI movieRepositoryI;
    private final String API_URL = "http://www.omdbapi.com/?apikey=b79fdda2&t=";
    public void addMovie(String title,String trailerUrl) {
        try {
            String url = API_URL + title;
            RestTemplate rt = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie = mapper.readValue(rt.getForObject(url, String.class), Movie.class);
            System.out.println(movie.toString());

            URL url1 = new URL(movie.getPoster());
            Image image = ImageIO.read(url1);
            byte[] content = url1.openStream().readAllBytes();
            movie.setImageData(content);
            movie.setTrailerUrl(trailerUrl);
            movieRepositoryI.save(movie);

        }catch(Exception e){
            System.out.println("Exception occured :"+e);
        }


    }





    public Movie getMovieDetails(String title){

        return movieRepositoryI.getReferenceByTitle(title);
    }
    public List<Movie> getMovies(){

        return movieRepositoryI.findAll();
    }
    public Movie save(Movie movie) {
        try {
            String url = "http://www.omdbapi.com/?apikey=b79fdda2&t=" + movie.getTitle();
            RestTemplate rt = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie1 = mapper.readValue(rt.getForObject(url, String.class), Movie.class);
            System.out.println(movie.toString());

            URL url1 = new URL(movie.getPoster());
            Image image = ImageIO.read(url1);
            byte[] content = url1.openStream().readAllBytes();
            movie.setImageData(content);
            movieRepositoryI.save(movie);
        }catch (Exception e){

        }
        movieRepositoryI.save(movie);
        return movie;
    }
    //  public byte[] getImage(String name) {

       // byte[] image = decompressImage(movieRepositoryI.get().getImageData());
       // return image;
  //  }
 /*   public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }*/
}
