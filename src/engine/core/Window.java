package engine.core;

import javax.swing.*;

import game.objects.Camera;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

public class Window extends JPanel {

    private BufferedImage imagem;
    private int largura = 400;
    private int altura = 400;

    public Camera cam = new Camera();

    public Window() {
        imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(largura, altura));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                largura = getWidth();
                altura = getHeight();
                BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = novaImagem.createGraphics();
                g.drawImage(imagem, 0, 0, largura, altura, null);
                g.dispose();
                imagem = novaImagem;
                setPreferredSize(new Dimension(largura, altura));
                revalidate();
                repaint();
            }
        });

        try {
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("config/config.properties");
            properties.load(input);
            input.close();

            String title = properties.getProperty("game.title");
            int windowWidth = Integer.parseInt(properties.getProperty("window.width"));
            int windowHeight = Integer.parseInt(properties.getProperty("window.height"));

            JFrame frame = new JFrame(title);
            new KeyControlls(frame, cam);
            frame.setSize(windowWidth, windowHeight);
            frame.add(this);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagem, 0, 0, this);
    }

    public void drawPixel(int x, int y, int size, Color cor) {

        for (int i = -(size / 2); i < size / 2; i++) {
            for (int j = -(size / 2); j < size / 2; j++) {
                if (x + i >= 0 && x + i < largura && y + j >= 0 && y + j < altura) {
                    imagem.setRGB(x + i, y + j, cor.getRGB());
                }
                repaint();
            }
        }
    }

    public void clear() {
        imagem = new BufferedImage(this.largura, this.altura, BufferedImage.TYPE_INT_RGB);
    }
}