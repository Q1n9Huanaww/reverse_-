package mobi.oneway.export.feed;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import mobi.oneway.export.AdListener.feed.OWFeedAdEventListener;
import mobi.oneway.export.AdListener.feed.OWFeedVideoAdListener;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.p086a.C1933b;

/* JADX INFO: renamed from: mobi.oneway.export.feed.a */
/* JADX INFO: loaded from: classes.dex */
public class C1946a implements IFeedAd {

    /* JADX INFO: renamed from: a */
    private IFeedAd f4996a;

    /* JADX INFO: renamed from: b */
    private C1931a f4997b;

    public C1946a(IFeedAd iFeedAd, C1931a c1931a) {
        this.f4996a = iFeedAd;
        this.f4997b = c1931a;
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public String getIconImage() {
        return this.f4996a.getIconImage();
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public List<String> getImages() {
        return this.f4996a.getImages();
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public String getTitle() {
        return this.f4996a.getTitle();
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public int getType() {
        return this.f4996a.getType();
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public View getVideoView() {
        return this.f4996a.getVideoView();
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public void handleAdEvent(ViewGroup viewGroup, OWFeedAdEventListener oWFeedAdEventListener) {
        this.f4996a.handleAdEvent(viewGroup, new C1933b(this.f4997b, oWFeedAdEventListener));
    }

    @Override // mobi.oneway.export.feed.IFeedAd
    public void setVideoAdListener(OWFeedVideoAdListener oWFeedVideoAdListener) {
    }
}
