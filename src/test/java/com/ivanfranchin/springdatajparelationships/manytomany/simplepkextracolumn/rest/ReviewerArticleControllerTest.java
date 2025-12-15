package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest;

import com.ivanfranchin.springdatajparelationships.MyContainers;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Article;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Comment;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Reviewer;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.repository.ArticleRepository;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.repository.CommentRepository;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.repository.ReviewerRepository;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.ArticleResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CommentResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CreateArticleRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CreateCommentRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CreateReviewerRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.ReviewerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestRestTemplate
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
)
@ImportTestcontainers(MyContainers.class)
class ReviewerArticleControllerTest implements MyContainers {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        reviewerRepository.deleteAll();
        articleRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    void testGetReviewer() {
        Reviewer reviewer = reviewerRepository.save(getDefaultReviewer());

        String url = String.format(API_REVIEWERS_REVIEWER_ID_URL, reviewer.getId());
        ResponseEntity<ReviewerResponse> responseEntity = testRestTemplate.getForEntity(url, ReviewerResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(reviewer.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(reviewer.getName());
        assertThat(responseEntity.getBody().comments().size()).isEqualTo(0);
    }

    @Test
    void testCreateReviewer() {
        CreateReviewerRequest createReviewerRequest = new CreateReviewerRequest("Ivan Franchin");
        ResponseEntity<ReviewerResponse> responseEntity = testRestTemplate.postForEntity(
                API_REVIEWERS_URL, createReviewerRequest, ReviewerResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createReviewerRequest.name());
        assertThat(responseEntity.getBody().comments().size()).isEqualTo(0);

        Optional<Reviewer> reviewerOptional = reviewerRepository.findById(responseEntity.getBody().id());
        assertThat(reviewerOptional.isPresent()).isTrue();
        reviewerOptional.ifPresent(r -> assertThat(r.getName()).isEqualTo(createReviewerRequest.name()));
    }

    @Test
    void testDeleteReviewer() {
        Reviewer reviewer = reviewerRepository.save(getDefaultReviewer());

        String url = String.format(API_REVIEWERS_REVIEWER_ID_URL, reviewer.getId());
        ResponseEntity<ReviewerResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, ReviewerResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(reviewer.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(reviewer.getName());
        assertThat(responseEntity.getBody().comments().size()).isEqualTo(0);

        Optional<Reviewer> reviewerOptional = reviewerRepository.findById(reviewer.getId());
        assertThat(reviewerOptional.isPresent()).isFalse();
    }

    @Test
    void testGetArticle() {
        Article article = articleRepository.save(getDefaultArticle());

        String url = String.format(API_ARTICLES_ARTICLE_ID_URL, article.getId());
        ResponseEntity<ArticleResponse> responseEntity = testRestTemplate.getForEntity(url, ArticleResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(article.getId());
        assertThat(responseEntity.getBody().title()).isEqualTo(article.getTitle());
        assertThat(responseEntity.getBody().comments().size()).isEqualTo(0);
    }

    @Test
    void testCreateArticle() {
        CreateArticleRequest createArticleRequest = new CreateArticleRequest("MySQL, new features");
        ResponseEntity<ArticleResponse> responseEntity = testRestTemplate.postForEntity(
                API_ARTICLES_URL, createArticleRequest, ArticleResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().title()).isEqualTo(createArticleRequest.title());
        assertThat(responseEntity.getBody().comments().size()).isEqualTo(0);

        Optional<Article> articleOptional = articleRepository.findById(responseEntity.getBody().id());
        assertThat(articleOptional.isPresent()).isTrue();
        articleOptional.ifPresent(a -> assertThat(a.getTitle()).isEqualTo(createArticleRequest.title()));
    }

    @Test
    void testDeleteArticle() {
        Article article = articleRepository.save(getDefaultArticle());

        String url = String.format(API_ARTICLES_ARTICLE_ID_URL, article.getId());
        ResponseEntity<ArticleResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, ArticleResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(article.getId());
        assertThat(responseEntity.getBody().title()).isEqualTo(article.getTitle());
        assertThat(responseEntity.getBody().comments().size()).isEqualTo(0);

        Optional<Article> articleOptional = articleRepository.findById(article.getId());
        assertThat(articleOptional.isPresent()).isFalse();
    }

    @Test
    void testGetComment() {
        Reviewer reviewer = reviewerRepository.save(getDefaultReviewer());
        Article article = articleRepository.save(getDefaultArticle());

        Comment comment = getDefaultComment();
        comment.setReviewer(reviewer);
        comment.setArticle(article);
        comment = commentRepository.save(comment);

        String url = String.format(API_COMMENTS_COMMENT_ID_URL, comment.getId());
        ResponseEntity<CommentResponse> responseEntity = testRestTemplate.getForEntity(url, CommentResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(comment.getId());
        assertThat(responseEntity.getBody().text()).isEqualTo(comment.getText());
        assertThat(responseEntity.getBody().reviewer().id()).isEqualTo(reviewer.getId());
        assertThat(responseEntity.getBody().reviewer().name()).isEqualTo(reviewer.getName());
        assertThat(responseEntity.getBody().article().id()).isEqualTo(article.getId());
        assertThat(responseEntity.getBody().article().title()).isEqualTo(article.getTitle());
    }

    @Test
    void testCreateComment() {
        Reviewer reviewer = reviewerRepository.save(getDefaultReviewer());
        Article article = articleRepository.save(getDefaultArticle());

        CreateCommentRequest createCommentRequest = new CreateCommentRequest(
                reviewer.getId(), article.getId(), "This is a very good article");
        ResponseEntity<CommentResponse> responseEntity = testRestTemplate.postForEntity(
                API_COMMENTS_URL, createCommentRequest, CommentResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().text()).isEqualTo(createCommentRequest.text());
        assertThat(responseEntity.getBody().reviewer().id()).isEqualTo(reviewer.getId());
        assertThat(responseEntity.getBody().article().id()).isEqualTo(article.getId());

        Optional<Comment> commentOptional = commentRepository.findById(responseEntity.getBody().id());
        assertThat(commentOptional.isPresent()).isTrue();
        commentOptional.ifPresent(c -> {
            assertThat(c.getText()).isEqualTo(createCommentRequest.text());
            assertThat(c.getReviewer().getId()).isEqualTo(reviewer.getId());
            assertThat(c.getArticle().getId()).isEqualTo(article.getId());
        });

        Optional<Reviewer> reviewerOptional = reviewerRepository.findById(reviewer.getId());
        assertThat(reviewerOptional.isPresent()).isTrue();
        reviewerOptional.ifPresent(r -> {
            assertThat(r.getComments().size()).isEqualTo(1);
            commentOptional.ifPresent(c -> assertThat(r.getComments().contains(c)).isTrue());
        });

        Optional<Article> articleOptional = articleRepository.findById(article.getId());
        assertThat(articleOptional.isPresent()).isTrue();
        articleOptional.ifPresent(a -> {
            assertThat(a.getComments().size()).isEqualTo(1);
            commentOptional.ifPresent(c -> assertThat(a.getComments().contains(c)).isTrue());
        });
    }

    @Test
    void testDeleteComment() {
        Reviewer reviewer = reviewerRepository.save(getDefaultReviewer());
        Article article = articleRepository.save(getDefaultArticle());

        Comment comment = getDefaultComment();
        comment.setReviewer(reviewer);
        comment.setArticle(article);
        comment = commentRepository.save(comment);

        String url = String.format(API_COMMENTS_COMMENT_ID_URL, comment.getId());
        ResponseEntity<CommentResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, CommentResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(comment.getId());
        assertThat(responseEntity.getBody().reviewer().id()).isEqualTo(comment.getReviewer().getId());
        assertThat(responseEntity.getBody().article().id()).isEqualTo(comment.getArticle().getId());

        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
        assertThat(commentOptional.isPresent()).isFalse();

        Optional<Reviewer> reviewerOptional = reviewerRepository.findById(reviewer.getId());
        assertThat(reviewerOptional.isPresent()).isTrue();
        reviewerOptional.ifPresent(r -> assertThat(r.getComments().size()).isEqualTo(0));

        Optional<Article> articleOptional = articleRepository.findById(article.getId());
        assertThat(articleOptional.isPresent()).isTrue();
        articleOptional.ifPresent(a -> assertThat(a.getComments().size()).isEqualTo(0));
    }

    private Reviewer getDefaultReviewer() {
        Reviewer reviewer = new Reviewer();
        reviewer.setName("Ivan Franchin");
        return reviewer;
    }

    private Article getDefaultArticle() {
        Article article = new Article();
        article.setTitle("Article about Springboot test");
        return article;
    }

    private Comment getDefaultComment() {
        Comment comment = new Comment();
        comment.setText("Good article");
        return comment;
    }

    private static final String API_REVIEWERS_URL = "/api/reviewers";
    private static final String API_REVIEWERS_REVIEWER_ID_URL = "/api/reviewers/%s";
    private static final String API_ARTICLES_URL = "/api/articles";
    private static final String API_ARTICLES_ARTICLE_ID_URL = "/api/articles/%s";
    private static final String API_COMMENTS_URL = "/api/comments";
    private static final String API_COMMENTS_COMMENT_ID_URL = "/api/comments/%s";
}