package Controller;

public class FileController {
	private static FileController fileController;
	
	private FileController() {
		
	}
	
	public static FileController getInstance() {
		if(fileController == null) {
			fileController = new FileController();
		}
		
		return fileController;
	}
}
