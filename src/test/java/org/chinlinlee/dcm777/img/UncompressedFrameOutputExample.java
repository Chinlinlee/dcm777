package org.chinlinlee.dcm777.img;

import org.github.chinlinlee.dcm777.img.UncompressedFrameOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class UncompressedFrameOutputExample {
    public static void main(String[] args) {
        if (args.length >= 2) {
            String dicomPath = args[0];
            int frameNumber = Integer.parseInt(args[1]);
            String outputPath = args.length > 2 ? args[2] : "output_frame.raw";

            extractFrame(dicomPath, frameNumber, outputPath);
        } else {
            extractFrameInteractive();
        }
    }

    private static void extractFrameInteractive() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("請輸入 DICOM 檔案路徑 (Enter DICOM file path): ");
            String dicomPath = scanner.nextLine().trim();

            if (!Files.exists(Paths.get(dicomPath))) {
                System.err.println("錯誤: 檔案不存在 - " + dicomPath);
                return;
            }

            System.out.print("請輸入要提取的 frame 編號 (從 1 開始) (Enter frame number, starting from 1): ");
            int frameNumber = scanner.nextInt();
            scanner.nextLine(); // 清除換行符 / Clear newline

            if (frameNumber < 1) {
                System.err.println("錯誤: frame 編號必須大於等於 1");
                return;
            }

            // 輸入輸出路徑 / Input output path
            System.out.print("請輸入輸出檔案路徑 (按 Enter 使用預設 'output_frame.raw') (Enter output path or press Enter for default): ");
            String outputPath = scanner.nextLine().trim();
            if (outputPath.isEmpty()) {
                outputPath = "output_frame.raw";
            }

            // 執行提取 / Execute extraction
            extractFrame(dicomPath, frameNumber, outputPath);
        } catch(Exception e) {
            System.err.println("錯誤: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    /**
     * 
     * @param dicomPath DICOM 檔案路徑
     * @param frameNumber frame 編號 (從 1 開始)
     * @param outputPath 輸出檔案路徑
     */
    private static void extractFrame(String dicomPath, int frameNumber, String outputPath) {
        System.out.println("\n=== 開始提取 frame / Starting frame extraction ===");
        System.out.println("DICOM 檔案: " + dicomPath);
        System.out.println("Frame 編號: " + frameNumber);
        System.out.println("輸出路徑: " + outputPath);
        System.out.println();

        Path dicomFilePath = Paths.get(dicomPath);
        if (!Files.exists(dicomFilePath)) {
            System.err.println("Error: DICOM 檔案不存在 / DICOM file not found: " + dicomPath);
            return;
        }

        if (!Files.isRegularFile(dicomFilePath)) {
            System.err.println("Error: DICOM 檔案不是一個檔案 / DICOM file is not a regular file: " + dicomPath);
            return;
        }

        if (frameNumber < 1) {
            System.err.println("錯誤: frame 編號必須大於等於 1");
            return;
        }

        try (UncompressedFrameOutput frameOutput = new UncompressedFrameOutput(dicomPath, frameNumber)) {
            OutputStream o = Files.newOutputStream(Paths.get(outputPath));
            System.out.println("Output stream: " + o);

            frameOutput.write(o);

            Path outputFilePath = Paths.get(outputPath);
            long fileSize = Files.size(outputFilePath);
            System.out.println("成功提取 frame!");
            System.out.println("  輸出檔案: " + outputPath);
            System.out.println("  檔案大小: " + fileSize + " bytes");

        } catch (IOException e) {
            System.err.println("提取失敗: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
