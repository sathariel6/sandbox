package blockchain.data.loaders;

class FileDataLoaderTest {

    @org.junit.jupiter.api.Test
    void loadingFileWorks() {
        FileDataLoader fileDataLoader = new FileDataLoader();
        fileDataLoader.loadUserData();

    }

}