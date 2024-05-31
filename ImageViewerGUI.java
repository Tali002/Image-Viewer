import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

// https://stackoverflow.com/questions/6810581/how-to-center-the-text-in-a-jlabel
//https://stackoverflow.com/questions/60976527/reading-image-path-with-imageio-read-to-be-displayed-in-a-jlabel
//https://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
//https://stackoverflow.com/questions/10105521/how-to-change-the-contrast-and-brightness-of-an-image-stored-as-pixel-values

public class ImageViewerGUI extends JFrame implements ActionListener {
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "C:\\Users\\alito\\IdeaProjects\\image_viewer";
    File file;

    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 720;
    int w = 1440;
    float brightenFactor = 0.5f;

     ImageViewerGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(new GridLayout(2, 1));
        JLabel imageLabel = new JLabel("Image Viewer",SwingConstants.CENTER);
        imageLabel.setFont(new Font("times new roman", Font.BOLD, 16));
        imageLabel.setForeground(Color.WHITE);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2,1,1));


        selectFileButton = new JButton("Choose Image");
        showImageButton = new JButton("Show Image");
        brightnessButton = new JButton("Brightness");
        grayscaleButton = new JButton("Gray Scale");
        resizeButton = new JButton("Resize");
        closeButton = new JButton("Exit");

        JButton[] buttons = {selectFileButton, showImageButton, brightnessButton, grayscaleButton, resizeButton, closeButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(new Color(150, 152, 250));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.addActionListener(this);
            buttonsPanel.add(button);
        }
        mainPanel.add(imageLabel);
        mainPanel.add(buttonsPanel);

        this.add(mainPanel);

    }

    public void resizePanel() {

        this.getContentPane().removeAll();

        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setBackground(Color.GRAY);
        resizePanel.setBounds(0, 0, 700, 300);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setBounds(250, 80, 60, 30);
        widthLabel.setFont(new Font("times new roman", Font.BOLD, 16));
        widthLabel.setForeground(Color.WHITE);
        resizePanel.add(widthLabel);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(250, 120, 60, 30);
        heightLabel.setFont(new Font("times new roman", Font.BOLD, 16));
        heightLabel.setForeground(Color.WHITE);
        resizePanel.add(heightLabel);

        widthTextField = new JTextField();
        widthTextField.setBounds(320, 80, 100, 30);
        resizePanel.add(widthTextField);

        heightTextField = new JTextField();
        heightTextField.setBounds(320, 120, 100, 30);
        resizePanel.add(heightTextField);

        showResizeButton = new JButton("Show Result");
        showResizeButton.setFont(new Font("Arial", Font.BOLD, 14));
        showResizeButton.setBackground(new Color(150, 152, 250));
        showResizeButton.setForeground(Color.WHITE);
        showResizeButton.setFocusPainted(false);
        showResizeButton.setBounds(250, 180, 170, 30);
        showResizeButton.addActionListener(this);

        resizePanel.add(showResizeButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(150, 152, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBounds(250, 220, 170, 25);
        backButton.addActionListener(this);
        resizePanel.add(backButton);

        this.add(resizePanel);
        this.revalidate();
        this.repaint();
    }


    public void brightnessPanel() {
        this.getContentPane().removeAll();

        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        brightnessPanel.setBackground(Color.GRAY);
        brightnessPanel.setBounds(0, 0, 700, 300);

        JLabel brightnessLabel = new JLabel("Brightness:");
        brightnessLabel.setBounds(250, 100, 80, 30);
        brightnessLabel.setFont(new Font("times new roman", Font.BOLD, 16));
        brightnessLabel.setForeground(Color.WHITE);
        brightnessPanel.add(brightnessLabel);

        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(340, 100, 90, 30);
        brightnessPanel.add(brightnessTextField);

        showBrightnessButton = new JButton("Apply Brightness");
        showBrightnessButton.setBounds(250, 150, 180, 30);
        showBrightnessButton.setBackground(new Color(150, 152, 250));
        showBrightnessButton.setFont(new Font("Arial", Font.BOLD, 14));
        showBrightnessButton.setForeground(Color.WHITE);
        showBrightnessButton.setFocusPainted(false);
        showBrightnessButton.addActionListener(this);
        brightnessPanel.add(showBrightnessButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(150, 152, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBounds(250, 190, 180, 30);
        backButton.addActionListener(this);
        brightnessPanel.add(backButton);

        this.add(brightnessPanel);
        this.revalidate();
        this.repaint();
    }


    public void chooseFileImage() {
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }}

    public void showOriginalImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            BufferedImage image = ImageIO.read(file);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            tempPanel.add(imageLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        tempPanel.setSize(1440, 720);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1440, 720);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }


    public void grayScaleImage() {
        JFrame tempFrame = new JFrame("Image Viewer - Grayscale");
        JPanel tempPanel = new JPanel();
        try {
            BufferedImage image = ImageIO.read(file);
            ColorConvertOp rescaleOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            image = rescaleOp.filter(image, image);

            JLabel imageLabel = new JLabel(new ImageIcon(image));

            tempPanel.setSize(1440, 720);
            tempFrame.setSize(1440, 720);
            tempPanel.add(imageLabel);

            tempFrame.add(tempPanel);
            tempFrame.pack();

            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}

    public void showResizeImage(int w, int h) {
        JFrame tempFrame = new JFrame("Image Viewer - Resized");
        JPanel tempPanel = new JPanel();
        try {
            BufferedImage image = ImageIO.read(file);
            BufferedImage resizedImage = new BufferedImage(w, h, image.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(image, 0, 0, w, h, null);
            g.dispose();

            JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));

            tempPanel.setSize(1440, 720);
            tempFrame.setSize(1440, 720);
            tempPanel.add(imageLabel);

            tempFrame.add(tempPanel);
            tempFrame.pack();
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}

    public void showBrightnessImage(float f) {
        JFrame tempFrame = new JFrame("Image Viewer - Brightness Adjusted");
        JPanel tempPanel = new JPanel();
        try {
            BufferedImage image = ImageIO.read(file);
            RescaleOp rescaleOp = new RescaleOp(f, 0, null);
            image = rescaleOp.filter(image, image);

            JLabel imageLabel = new JLabel(new ImageIcon(image));

            tempPanel.setSize(1440, 720);
            tempFrame.setSize(1440, 720);
            tempPanel.add(imageLabel);
            tempFrame.add(tempPanel);

            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            resizePanel();
        }else if(e.getSource()== showImageButton){
            showOriginalImage();
        }else if(e.getSource()==grayscaleButton){
            grayScaleImage();
        }else if(e.getSource()== showResizeButton){
            h = Integer.parseInt(heightTextField.getText());
            w = Integer.parseInt(widthTextField.getText());
            showResizeImage( w , h );
        }else if(e.getSource()==brightnessButton){
            brightnessPanel();
        }else if(e.getSource()== showBrightnessButton){
            try {
                float brightness = Float.parseFloat(brightnessTextField.getText());
                if(brightness>0.5) brightness++;
                else if (brightness==0.5) brightness=1;
                // because the range is from 0 to 1, 0.5 to 1 make the image brighter.
                showBrightnessImage(brightness);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for brightness.");
            }}else if(e.getSource() == selectFileButton) {
                chooseFileImage();
        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
