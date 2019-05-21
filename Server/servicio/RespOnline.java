package servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class RespOnline {

	public static void ok(Socket cliente) throws IOException {
		OutputStream output = cliente.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		writer.println("Todo OK");
	}

	public static void auterror(Socket cliente, String error) throws IOException {
		OutputStream output = cliente.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		writer.println(error);
	}

	public static void veriferror(Socket cliente, String error) throws IOException {
		OutputStream output = cliente.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		writer.println(error);
	}
}
