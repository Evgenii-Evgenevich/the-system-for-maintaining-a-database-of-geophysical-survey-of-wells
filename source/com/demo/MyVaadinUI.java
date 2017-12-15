package com.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by EE on 13.12.2017.
 */
@SpringUI
@Title("The SfMDoGSoWells")
@Theme("valo")
public class MyVaadinUI extends UI {

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MyVaadinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
        @Override
        public ServletContext getServletContext() {
            return super.getServletContext();
        }
    }

    public class Uploader implements Upload.Receiver, Upload.SucceededListener {
        public File file = null;

        public OutputStream receiveUpload(String filename, String mimeType) {
            FileOutputStream fos = null;

            try {
                ServletContext sCtx = VaadinServlet.getCurrent().getServletContext();
                String dirName = sCtx.getRealPath("tmp");
                File dir = new File(dirName);
                if (!dir.exists()) dir.mkdirs();
                File longPath = new File(filename);
                filename = longPath.getName();
                file = new File(dir, filename);

                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return fos;
        }

        public void uploadSucceeded(Upload.SucceededEvent event) {
            String wellfieldName = wellfieldTextField.getValue();

            Wellfield wellfield = wellfieldRepository.findFirstByTitle(wellfieldName);

            if (wellfield == null) {
                wellfield = new Wellfield(wellfieldName);

                wellfieldRepository.save(wellfield);
            }

            String regionName = regionTextField.getValue();

            Region region = regionRepository.findFirstByName(regionName);

            if (region == null) {
                region = new Region(regionName);

                regionRepository.save(region);
            }

            List<Well> wellResult = DataLoader.loadFromLas(event.getFilename(), wellfield, region);

            wellRepository.save(wellResult);
        }
    }

    @Autowired
    WellRepository wellRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    WellfieldRepository wellfieldRepository;

    Region currentRegion;
    Wellfield currentWellfield;

    Grid<Well> wellGrid = new Grid<>(Well.class);

    private CheckBox regionCheckBox = new CheckBox("filter by regions");
    private CheckBox wellfieldCheckBox = new CheckBox("filter by wellfields");
    private ComboBox<Region> regionComboBox = new ComboBox<>("Regions");
    private ComboBox<Wellfield> wellfieldComboBox = new ComboBox<>("Wellfields");
    private Button download = new Button("download", e -> download());

    private TextField regionTextField = new TextField("Region");
    private TextField wellfieldTextField = new TextField("Wellfield");
    private Uploader uploader = new Uploader();
    private Upload upload = new Upload("upload", uploader);

    @Override
    protected void init(VaadinRequest request) {
        upload.addSucceededListener(uploader);

        updateRegionComboBox();
        regionComboBox.addValueChangeListener(e -> updateCurrentRegion());
        updateWellfieldComboBox();
        wellfieldComboBox.addValueChangeListener(e -> updateCurrentWellfield());
        updateWellGrid();

        VerticalLayout downloadLayout = new VerticalLayout(regionCheckBox, wellfieldCheckBox, regionComboBox, wellfieldComboBox, download);

        VerticalLayout uploadLayout = new VerticalLayout(regionTextField, wellfieldTextField, upload);

        HorizontalLayout layout = new HorizontalLayout(wellGrid, downloadLayout, uploadLayout);

        setContent(layout);
    }

    private void updateWellGrid() {
        List<Well> wells;

        boolean byregion = regionCheckBox.getValue();
        boolean bywellfield = wellfieldCheckBox.getValue();

        if (byregion && bywellfield) {
            wells = wellRepository.findAllByRegionAndWellfield(currentRegion, currentWellfield);
        } else if (byregion && !bywellfield) {
            wells = wellRepository.findAllByRegion(currentRegion);
        } else if (!byregion && bywellfield) {
            wells = wellRepository.findAllByWellfield(currentWellfield);
        } else {
            wells = wellRepository.findAll();
        }

        wellGrid.setItems(wells);
    }

    private void updateRegionComboBox() {
        List<Region> regions = regionRepository.findAll();

        regionComboBox.setItems(regions);

        updateCurrentRegion();
    }

    private void updateCurrentRegion() {
        currentRegion = regionComboBox.getValue();
    }

    private void updateWellfieldComboBox() {
        List<Wellfield> wellfields = wellfieldRepository.findAll();

        wellfieldComboBox.setItems(wellfields);

        updateCurrentWellfield();
    }

    private void updateCurrentWellfield() {
        currentWellfield = wellfieldComboBox.getValue();
    }

    private void download() {
    }

}
