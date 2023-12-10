package edu.project4.processing;

import edu.project4.model.FractalImage;

// пост-обработка in-place, например, гамма-коррекция
@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
