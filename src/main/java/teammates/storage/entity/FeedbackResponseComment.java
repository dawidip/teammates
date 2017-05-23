package teammates.storage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Text;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import teammates.common.datatransfer.CommentSendingState;
import teammates.common.datatransfer.FeedbackParticipantType;
import teammates.common.util.SanitizationHelper;

/**
 * An association class that represents the association
 * Giver --> [comments about] --> FeedbackResponse.
 * Currently giver is restricted only to Instructors.
 */
@Entity
@Index
public class FeedbackResponseComment extends BaseEntity {

    @Id
    private transient Long feedbackResponseCommentId;

    /** The foreign key to locate the Course object. */
    private String courseId;

    /** The foreign key to locate the FeedbackSession object. */
    private String feedbackSessionName;

    /** The foreign key to locate the FeedbackQuestion object. */
    private String feedbackQuestionId;

    /** The course-specific email used by the giver of the comment. */
    private String giverEmail;

    /** The foreign key to locate the FeedbackResponse object commented on. */
    private String feedbackResponseId;

    /** Is this comment pending to be sent to recipient (through email) or sending or sent. */
    private CommentSendingState sendingState;

    /** Response giver section. */
    private String giverSection;

    /** Response receiver section. */
    private String receiverSection;

    private List<FeedbackParticipantType> showCommentTo = new ArrayList<FeedbackParticipantType>();

    private List<FeedbackParticipantType> showGiverNameTo = new ArrayList<FeedbackParticipantType>();

    private Boolean isVisibilityFollowingFeedbackQuestion;

    /** The creation time of this comment. */
    private Date createdAt;

    /** The comment from giver about the feedback response. */
    @Unindex
    private Text commentText;

    /** The e-mail of the account that last edited the comment. */
    private String lastEditorEmail;

    /** The time in which the comment is last edited. */
    private Date lastEditedAt;

    @SuppressWarnings("unused") // required by Objectify
    private FeedbackResponseComment() {
    }

    public FeedbackResponseComment(String courseId, String feedbackSessionName,
            String feedbackQuestionId, String giverEmail, String feedbackResponseId,
            CommentSendingState sendingState, Date createdAt, Text commentText,
            String giverSection, String receiverSection, List<FeedbackParticipantType> showCommentTo,
            List<FeedbackParticipantType> showGiverNameTo, String lastEditorEmail, Date lastEditedAt) {
        this.feedbackResponseCommentId = null; // Auto generated by GAE
        this.courseId = courseId;
        this.feedbackSessionName = feedbackSessionName;
        this.feedbackQuestionId = feedbackQuestionId;
        this.giverEmail = giverEmail;
        this.feedbackResponseId = feedbackResponseId;
        this.sendingState = sendingState;
        this.createdAt = createdAt;
        this.commentText = SanitizationHelper.sanitizeForRichText(commentText);
        this.giverSection = giverSection;
        this.receiverSection = receiverSection;
        this.showCommentTo = showCommentTo == null ? new ArrayList<FeedbackParticipantType>() : showCommentTo;
        this.showGiverNameTo = showGiverNameTo == null ? new ArrayList<FeedbackParticipantType>() : showGiverNameTo;
        this.isVisibilityFollowingFeedbackQuestion = false;
        this.lastEditorEmail = lastEditorEmail == null ? giverEmail : lastEditorEmail;
        this.lastEditedAt = lastEditedAt == null ? createdAt : lastEditedAt;
    }

    /**
     * Use only if the comment already persisted in the datastore and id generated by GAE.
     * @return the feedbackResponseCommentId
     */
    public Long getFeedbackResponseCommentId() {
        return feedbackResponseCommentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFeedbackSessionName() {
        return feedbackSessionName;
    }

    public void setFeedbackSessionName(String feedbackSessionName) {
        this.feedbackSessionName = feedbackSessionName;
    }

    public String getFeedbackQuestionId() {
        return feedbackQuestionId;
    }

    public void setFeedbackQuestionId(String feedbackQuestionId) {
        this.feedbackQuestionId = feedbackQuestionId;
    }

    public Boolean getIsVisibilityFollowingFeedbackQuestion() {
        return this.isVisibilityFollowingFeedbackQuestion;
    }

    public void setIsVisibilityFollowingFeedbackQuestion(Boolean isVisibilityFollowingFeedbackQuestion) {
        this.isVisibilityFollowingFeedbackQuestion = isVisibilityFollowingFeedbackQuestion;
    }

    public String getGiverEmail() {
        return giverEmail;
    }

    /**
     * Sets the giver email of the response comment.
     *
     * @param giverEmail the giverEmail to set.
     *         This is the email used by the user in the course, not the one associated with the user's google account.
     */
    public void setGiverEmail(String giverEmail) {
        this.giverEmail = giverEmail;
    }

    public void setShowCommentTo(List<FeedbackParticipantType> showCommentTo) {
        this.showCommentTo = showCommentTo;
    }

    public List<FeedbackParticipantType> getShowCommentTo() {
        return showCommentTo;
    }

    public void setShowGiverNameTo(List<FeedbackParticipantType> showGiverNameTo) {
        this.showGiverNameTo = showGiverNameTo;
    }

    public List<FeedbackParticipantType> getShowGiverNameTo() {
        return showGiverNameTo;
    }

    public String getFeedbackResponseId() {
        return feedbackResponseId;
    }

    public void setFeedbackResponseId(String feedbackResponseId) {
        this.feedbackResponseId = feedbackResponseId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public CommentSendingState getSendingState() {
        return sendingState;
    }

    public void setSendingState(CommentSendingState sendingState) {
        this.sendingState = sendingState;
    }

    public Text getCommentText() {
        return commentText;
    }

    public void setCommentText(Text commentText) {
        this.commentText = commentText;
    }

    public String getGiverSection() {
        return giverSection;
    }

    public void setGiverSection(String giverSection) {
        this.giverSection = giverSection;
    }

    public String getReceiverSection() {
        return receiverSection;
    }

    public void setReceiverSection(String receiverSection) {
        this.receiverSection = receiverSection;
    }

    public void setLastEditorEmail(String lastEditorEmail) {
        this.lastEditorEmail = lastEditorEmail;
    }

    public String getLastEditorEmail() {
        return this.lastEditorEmail;
    }

    public Date getLastEditedAt() {
        return this.lastEditedAt;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
}
