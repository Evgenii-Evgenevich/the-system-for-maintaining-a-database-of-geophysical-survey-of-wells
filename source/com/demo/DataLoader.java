package com.demo;

import com.github.petvana.liblas.LasHeader;
import com.github.petvana.liblas.LasPoint;
import com.github.petvana.liblas.LasReader;
import com.github.petvana.liblas.LasWriter;
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

    private boolean initialized = false;

    /*private WellRepository wellRepository;

    public boolean isInitialized() {
        return initialized;
    }

    public DataLoader(WellRepository wellRepository) {
        try {
            // Initialize the native library
            LASlibJNI.initialize();

            initialized = true;

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.wellRepository = wellRepository;
    }*/

    public List<Well> loadFromLas0ld(String lasfile, Wellfield wellfield, Region region) {
        List<Well> result = new ArrayList<>();

        if (initialized) {
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
                                point.getZ(),
                                (short) point.getIntensity(),
                                point.getGPSTime()
                        );

                        result.add(well);
                    }
                }
            }
        }

        //wellRepository.save(result);

        return result;
    }

    static public List<Well> loadFromLas(String lasfile, Wellfield wellfield, Region region) {
        List<Well> result = new ArrayList<>();

        LasReader reader = new LasReader(lasfile);

        LasHeader header = reader.getHeader();

        long number = 0;

        LasPoint point;

        while ((point = reader.read()) != null) {
            ++number;

            Well well = new Well(
                    number,
                    wellfield,
                    region,
                    point.getX(),
                    point.getY(),
                    point.getZ(),
                    point.getIntensity(),
                    point.getTime()
            );

            result.add(well);
        }

        reader.close();

        return result;
    }


    static public boolean saveToLas(List<Well> wells, String lasfile) {
        boolean result = false;

        LasHeader header = new LasHeader();

        LasWriter writer = new LasWriter(lasfile, header);

        LasPoint point = new LasPoint(header);

        for (Well well : wells) {
            point.setX(well.getX());
            point.setY(well.getY());
            point.setZ(well.getZ());
            point.setIntensity(well.getIntensity());
            point.setTime(well.getTime());

            writer.write(point);
        }

        writer.close();

        return result;
    }
}
