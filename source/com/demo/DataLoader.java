package com.demo;

import org.lastools.LASHeader;
import org.lastools.LASPoint;
import org.lastools.LASReader;
import org.lastools.LASlibJNI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EE on 13.12.2017.
 */
public class DataLoader {

    boolean isInitialized = false;

    public DataLoader() {
        try {
            // Initialize the native library
            LASlibJNI.initialize();

            isInitialized = true;

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Well> loadFromLas(String lasfile, Wellfield wellfield, Region region) {
        List<Well> result = new ArrayList<>();

        if (isInitialized) {
            long number = 0;

            // Get an instance of LASReader for provided file
            try (LASReader reader = new LASReader(lasfile)) {

                // Get the header information of the file
                LASHeader header = reader.getHeader();

                // Check that the file is supported and in tact
                if (header.check()) {
                    // Ok, read points
                    while (reader.readPoint()) {
                        ++number;

                        LASPoint point = reader.getPoint();

                        Well well = new Well(
                                number,
                                wellfield,
                                region,
                                point.getX(),
                                point.getY(),
                                point.getZ()
                        );

                        result.add(well);
                    }
                }
            }
        }
        return result;
    }

    public boolean saveToLas(List<Well> wells, String lasfile) {
        boolean result = false;

        return result;
    }
}
