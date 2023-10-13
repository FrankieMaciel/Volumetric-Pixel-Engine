package engine.core;

import javax.swing.*;

import engine.entities.Camera;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

public class Window extends JPanel {

    private BufferedImage imagem;
    public int largura = 400;
    public int altura = 400;

    public Camera cam = new Camera();

    public Window() {

        try {
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("config/config.properties");
            properties.load(input);
            input.close();

            largura = Integer.parseInt(properties.getProperty("window.width"));
            altura = Integer.parseInt(properties.getProperty("window.height"));

            imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            setPreferredSize(new Dimension(largura, altura));

            String title = properties.getProperty("game.title");

            JFrame frame = new JFrame(title);
            new KeyControlls(frame, cam);
            frame.setSize(largura, altura);
            frame.add(this);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagem, 0, 0, this);
    }

    public void drawPixel(int x, int y, int size, Color cor) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (x + i >= 0 && x + i < largura && y + j >= 0 && y + j < altura) {

                    if (x + 10 + i > largura || x - 10 + i < 0) continue;
                    if (y + 10 + j > altura || y - 10 + j < 0) continue;

                    imagem.setRGB(x + i, y + j, cor.getRGB());
                }
                repaint();
            }
        }
    }

    public void clear() {
        repaint();
        imagem = new BufferedImage(this.largura, this.altura, BufferedImage.TYPE_INT_RGB);
    }
}