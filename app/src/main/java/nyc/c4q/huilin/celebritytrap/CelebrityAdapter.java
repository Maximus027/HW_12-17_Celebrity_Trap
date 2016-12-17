package nyc.c4q.huilin.celebritytrap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.huilin.celebritytrap.model.Celebrity;

/**
 * Created by huilin on 12/14/16.
 */
public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityViewHolder>{
    private List<Celebrity> celebs;
    private Listener listener;

    public CelebrityAdapter(List<Celebrity> celebs, Listener listener) {
        this.celebs = celebs;
        this.listener = listener;
    }

    @Override
    public CelebrityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celebrity, parent, false);
        return new CelebrityViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(CelebrityViewHolder holder, int position) {
        Celebrity celebrity = celebs.get(position);
        holder.bind(celebrity);

    }

    @Override
    public int getItemCount() {
        return celebs.size();
    }

    public void setData(List<Celebrity> celebrities) {
        this.celebs = celebrities;
        notifyDataSetChanged();
    }

    interface Listener {

        void onCelebrityClicked(Celebrity celeb);
        void onCelebrityLongClicked(Celebrity celeb);
    }

}
