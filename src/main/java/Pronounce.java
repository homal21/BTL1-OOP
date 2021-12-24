import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Pronounce {
    /**
     * voice.
     * @param text
     * @throws Exception
     */
    public void pronounce(String text) throws Exception {
        String api = "http://translate.google.com/translate_tts?ie=UTF-8&tl=" + "en" + "&client=tw-ob&q=" +
                URLEncoder.encode(text, String.valueOf(StandardCharsets.UTF_8));
        URL url = new URL(api);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream audio = con.getInputStream();
        new Player(audio).play();
        con.disconnect();
        
    }

}
