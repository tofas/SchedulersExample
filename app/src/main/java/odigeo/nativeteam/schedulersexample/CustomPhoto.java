package odigeo.nativeteam.schedulersexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daniel.morales on 12/6/17.
 */

public class CustomPhoto implements Parcelable{

	int albumId;
	int id;
	String title;
	String url;
	String thumbnailUrl;

	protected CustomPhoto(Parcel in) {

		albumId = in.readInt();
		id = in.readInt();
		title = in.readString();
		url = in.readString();
		thumbnailUrl = in.readString();
	}

	public static final Creator<CustomPhoto> CREATOR = new Creator<CustomPhoto>() {
		@Override
		public CustomPhoto createFromParcel(Parcel in) {

			return new CustomPhoto(in);
		}

		@Override
		public CustomPhoto[] newArray(int size) {

			return new CustomPhoto[size];
		}
	};

	public int getAlbumId() {

		return albumId;
	}

	public void setAlbumId(int albumId) {

		this.albumId = albumId;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public String getThumbnailUrl() {

		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {

		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {

		parcel.writeInt(albumId);
		parcel.writeInt(id);
		parcel.writeString(title);
		parcel.writeString(url);
		parcel.writeString(thumbnailUrl);
	}
}
