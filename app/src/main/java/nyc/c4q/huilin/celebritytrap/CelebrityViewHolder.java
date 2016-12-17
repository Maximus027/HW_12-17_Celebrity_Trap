package nyc.c4q.huilin.celebritytrap;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.huilin.celebritytrap.CelebrityAdapter.Listener;
import nyc.c4q.huilin.celebritytrap.model.Celebrity;

/**
 * Created by huilin on 12/14/16.
 */
public class CelebrityViewHolder extends RecyclerView.ViewHolder {

    private TextView celebName;
    private ImageView celebImgView;
    private View celebView;
    private Listener listener;

    public CelebrityViewHolder(View itemView, Listener listener) {
        super(itemView);
        this.celebView = itemView;
        this.listener = listener;
        celebImgView = (ImageView) itemView.findViewById(R.id.img_celeb);
        celebName = (TextView) itemView.findViewById(R.id.tv_celeb_name);

    }

    public void bind(final Celebrity celeb) {
        celebName.setText(celeb.getName());
        if (celeb.getImage() != 0) {
            Picasso.with(itemView.getContext())
                    .load(celeb.getImage())
                    .into(celebImgView);
        }

        celebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCelebrityClicked(celeb);
            }
        });

        celebView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                listener.onCelebrityLongClicked(celeb);
                return true;
            }
        });
    }
}
