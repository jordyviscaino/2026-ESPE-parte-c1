package es.upm.grise.profundizacion.file;

import java.util.ArrayList;
import java.util.List;

public class File {

    private FileType type;
    private List<Character> content;
    
    // Añadimos esta dependencia para poder usar los métodos de instancia de FileUtils
    private FileUtils fileUtils = new FileUtils();

    /*
     * Constructor
     */
    public File() {
        // Especificación: content deberá estar vacío, pero no null.
        this.content = new ArrayList<>();
    }

    /*
     * Method to code / test
     */
    public void addProperty(char[] newcontent) throws InvalidContentException, WrongFileTypeException {
        // Especificación: Si newcontent es null, se lanzará una InvalidContentException.
        if (newcontent == null) {
            throw new InvalidContentException();
        }

        // Especificación: Si el type del file es IMAGE, se lanzará una excepción WrongFileTypeException.
        if (this.type == FileType.IMAGE) {
            throw new WrongFileTypeException();
        }

        // Especificación: newcontent se añade al content existente.
        for (char c : newcontent) {
            this.content.add(c);
        }
    }

    /*
     * Method to code / test
     */
    public long getCRC32() {
        // Especificación: Si content está vacío, getCRC32() devolverá el valor 0.
        if (content.isEmpty()) {
            return 0L;
        }

        // Especificación: content debe transformarse en un byte[] antes de usar el método calculateCRC32().
        byte[] byteArray = new byte[content.size()];
        
        for (int i = 0; i < content.size(); i++) {
            char c = content.get(i);
            // Máscara 0xFF para obtener el byte menos significativo
            byteArray[i] = (byte) (c & 0xFF);
        }

        // CORRECCIÓN: Usamos la instancia 'fileUtils' en lugar de una llamada estática.
        return fileUtils.calculateCRC32(byteArray);
    }
    
    /*
     * Setters/getters
     */
    public void setType(FileType type) {
        this.type = type;
    }
    
    public List<Character> getContent() {
        return content;
    }
}