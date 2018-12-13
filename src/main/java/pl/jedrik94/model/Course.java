package pl.jedrik94.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor.getFirstName() + " " + instructor.getLastName() +
                '}';
    }

    public void addReview(Review review) {
        Optional<Review> tmpReview = Optional.ofNullable(review);

        prepareCommentList();

        tmpReview.ifPresent(this.reviews::add);
    }

    public void addReviews(Collection<Review> reviews) {
        Optional<Collection> tmpReview = Optional.ofNullable(reviews);

        prepareCommentList();

        tmpReview.ifPresent(this.reviews::addAll);
    }

    private void prepareCommentList() {
        if (!checkExistenceOfRequiredCommentList()) {
            createRequiredCommentList();
        }
    }

    private void createRequiredCommentList() {
        this.reviews = new ArrayList<>();
    }

    private boolean checkExistenceOfRequiredCommentList() {
        Optional<List> list = Optional.ofNullable(this.reviews);

        return list.isPresent();
    }
}
