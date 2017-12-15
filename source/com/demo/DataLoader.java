package com.demo;

import com.github.petvana.liblas.LasHeader;
import com.github.petvana.liblas.LasPoint;
import com.github.petvana.liblas.LasReader;
import com.github.petvana.liblas.LasWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EE on 13.12.2017.
 */
public class DataLoader {

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
        boolean result = true;

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
