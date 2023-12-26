public class ProcessCreation {
    private int nbReduc;
    private int nbMap;
    private String filename;

    public ProcessCreation(int nbReduc, int nbMap, String filename) {
        this.nbReduc = nbReduc;
        this.nbMap = nbMap;
        this.filename = filename;
    }
    // GETTERS AND SETTERS
    public int getNbReduc() {
        return nbReduc;
    }

    public void setNbReduc(int nbReduc) {
        this.nbReduc = nbReduc;
    }

    public int getNbMap() {
        return nbMap;
    }

    public void setNbMap(int nbMap) {
        this.nbMap = nbMap;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    // CREATE THE PROCESS WITH MULTIPROCESSING HERE
}
