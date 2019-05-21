package principal;

import java.net.*;

import java.io.*;

public class Main {

	public static void main(String[] args) {

		URL url;
		try {
			url = new URL("http://localhost/"); // URL local

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			return;
		}

		String hostname = url.getHost();
		int port = 6666;

		try (Socket socket = new Socket(hostname, port)) {

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			// entrada
			BufferedReader br = new BufferedReader(new FileReader("ventas2.txt"));
			String linea;
			int contador=0;
			// salida (socket)
			while ((linea = br.readLine()) != null) {
				writer.println(linea);
				contador++;
			}
			// cierro archivo
			br.close();

			InputStream input = socket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			String line;
			// respuesta del server
			/*while ((line = reader.readLine()) != null) {
				System.out.println(line);
				System.out.println("\n");
			}*/
			int aux=0;
			while(aux<contador) {
				line = reader.readLine();
				System.out.println(line);
				System.out.println("\n");
				aux++;
			}
			socket.close();
		} catch (UnknownHostException ex) {

			System.out.println("Server not found: " + ex.getMessage());

		} catch (IOException ex) {

			System.out.println("I/O error: " + ex.getMessage());
		}
	}
}
