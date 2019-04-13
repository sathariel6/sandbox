package data.loader;

import data.LendingPool;

import java.nio.file.Path;


public interface LenderLoader {

    LendingPool loadLenderData(Path csvFileLocation);
}
