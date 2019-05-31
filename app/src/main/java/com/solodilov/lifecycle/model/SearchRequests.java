package com.solodilov.lifecycle.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "foresmatic")
public class SearchRequests implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("quoteText")
    @ColumnInfo(name = "quoteText")
    private String quoteText;

    @SerializedName("quoteAuthor")
    @ColumnInfo(name = "quoteAuthor")
    private String quoteAuthor;

    @SerializedName("quoteLink")
    @ColumnInfo(name = "senderLink")
    private String senderLink;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public String getSenderLink() {
        return senderLink;
    }

    public void setSenderLink(String senderLink) {
        this.senderLink = senderLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchRequests that = (SearchRequests) o;
        return id == that.id &&
                Objects.equals(quoteText, that.quoteText) &&
                Objects.equals(quoteAuthor, that.quoteAuthor) &&
                Objects.equals(senderLink, that.senderLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quoteText, quoteAuthor, senderLink);
    }
}