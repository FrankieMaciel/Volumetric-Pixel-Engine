package engine.core;

import javax.swing.*;
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
            new KeyControlls(frame);
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

    public void drawPixel(int x, int y, Color cor) {
        if (x >= 0 && x < largura && y >= 0 && y < altura) {
            imagem.setRGB(x, y, cor.getRGB());
        }
        repaint();
    }

    public void clear() {
        imagem = new BufferedImage(this.largura, this.altura, BufferedImage.TYPE_INT_RGB);
    }
}