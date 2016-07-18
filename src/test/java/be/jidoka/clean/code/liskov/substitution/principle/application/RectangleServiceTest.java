package be.jidoka.clean.code.liskov.substitution.principle.application;

import be.jidoka.clean.code.liskov.substitution.principle.domain.Rectangle;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;

public class RectangleServiceTest {

    @Test
    public void shouldBeAbleToDoubleWidthOfAllRectangles() {
        final List<Rectangle> rectangles = ShapeFixture.generateRectangles();

        final List<Double> originalWidths = rectangles.stream()
                .mapToDouble(Rectangle::getWidth)
                .boxed()
                .collect(Collectors.toList());
        final List<Double> originalHeights = rectangles.stream()
                .mapToDouble(Rectangle::getHeight)
                .boxed()
                .collect(Collectors.toList());

        // Assertions are not strictly necessary here but they make explicit what we expect to change after we call doubleWidthOf().
        for (int i = 0; i < rectangles.size(); i++) {
            assertThat(rectangles.get(i).getWidth()).isEqualTo(originalWidths.get(i));
            assertThat(rectangles.get(i).getHeight()).isEqualTo(originalHeights.get(i));
        }

        doubleWidthOf(rectangles);

        for (int i = 0; i < rectangles.size(); i++) {
            assertThat(rectangles.get(i).getWidth()).isEqualTo(2 * originalWidths.get(i));
            // Height should not have changed.
            assertThat(rectangles.get(i).getHeight()).isEqualTo(originalHeights.get(i));
        }
    }

    private void doubleWidthOf(Collection<Rectangle> rectangles) {
        for (Rectangle rectangle : rectangles) {
            rectangle.setWidth(rectangle.getWidth() * 2);
        }
    }

}
