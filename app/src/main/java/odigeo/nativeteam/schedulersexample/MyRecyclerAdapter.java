package odigeo.nativeteam.schedulersexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomPhotoHolder> {

	private List<CustomPhoto> photos;
	private Context context;

	public MyRecyclerAdapter(Context context, List<CustomPhoto> photos) {
		this.context = context;
		this.photos = photos;
	}

	@Override
	public CustomPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_photo_item, parent, false);

		return new CustomPhotoHolder(view);
	}

	@Override
	public void onBindViewHolder(CustomPhotoHolder holder, int position) {
		holder.textViewTitle.setText(photos.get(position).getTitle());
		holder.textViewUrl.setText(photos.get(position).getUrl());
		Glide.with(context)
				.load(photos.get(position).getThumbnailUrl())
				.into(holder.imageViewThumbnail);
	}

	@Override
	public int getItemCount() {
		return photos.size();
	}

	public void refresh(List<CustomPhoto> photos) {
		this.photos.clear();
		notifyDataSetChanged();
		this.photos.addAll(photos);
		notifyDataSetChanged();
	}

	public static class CustomPhotoHolder extends RecyclerView.ViewHolder {

		public TextView textViewTitle;
		public TextView textViewUrl;
		public ImageView imageViewThumbnail;

		public CustomPhotoHolder(View itemView) {
			super(itemView);

			textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
			textViewUrl = (TextView) itemView.findViewById(R.id.textViewUrl);
			imageViewThumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
		}
	}
}
