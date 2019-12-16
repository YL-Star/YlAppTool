//package com.yl.ylapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.bigkoo.convenientbanner.ConvenientBanner;
//import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
//import com.bigkoo.convenientbanner.holder.Holder;
//import com.bigkoo.convenientbanner.listener.OnItemClickListener;
//import com.bumptech.glide.Glide;
//import com.hlkj.lnyxs.WebViewActivity.WebViewActivity_new;
//import com.hlkj.lnyxs.main.homefragment_version2.bean.HomeBanner;
//import com.hlkj.lnyxs.main.my_.bean.ActivityBean;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//
//import static com.hlkj.lnyxs.net.ServiceUrl.ServiceUrl;
//import static com.hlkj.lnyxs.net.ServiceUrl.lnfxoto;
//
///**
// * 轮播图
// * Created by yanglei on 2018/5/30;
// */
//public class HomeConvenientBannerV2 implements OnItemClickListener {
//
//    private Context context;
//    private ConvenientBanner convenientBanner;
//    //        private ArrayList<Integer> localImages = new ArrayList<Integer>();
//    private ArrayList<String> localImages = new ArrayList<String>();
//    private ArrayList<ActivityBean> ActList = new ArrayList<ActivityBean>();
//    HomeBanner activityBean;
//
//    public void setConvenientBanner(Context context, final ConvenientBanner convenientBanner, HomeBanner activityBean) {
//        this.context = context;
//        this.convenientBanner = convenientBanner;
//        this.activityBean = activityBean;
//        for (HomeBanner.DataBean act : activityBean.getData()) {
//            String url = ServiceUrl + lnfxoto + act.getImg();
//            this.localImages.add(url);
//        }
//
//        //        this.localImages.add(R.mipmap.nomal);
////        this.localImages.add(R.mipmap.nomal);
////        this.localImages.add(R.mipmap.nomal);
////        this.localImages.add(R.mipmap.nomal);
//        //开始自动翻页
//        convenientBanner.setPages(new CBViewHolderCreator() {
//            @Override
//            public Object createHolder() {
//                return new LocalImageHolderView();
//            }
//        }, this.localImages)
//                //设置指示器是否可见
//                .setPointViewVisible(true)
//                //设置自动切换（同时设置了切换时间间隔）
//                .startTurning(4000)
//
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.juxing_converierntbanner_pages, R.drawable.juxing_converierntbanner_page_check})
//                //设置指示器的方向（左、中、右）
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
//
//                //设置点击监听事件
//                .setOnItemClickListener(this)
//
//                //设置手动影响（设置了该项无法手动切换）
//                .setManualPageable(true);
//
//        if (this.localImages.size() < 2) {
//            convenientBanner.setCanLoop(false);
//            convenientBanner.setPageIndicator(new int[]{R.drawable.juxing_converierntbanner_pagesize_one, R.drawable.juxing_converierntbanner_pagesize_one});
//        }
//        //设置翻页的效果，不需要翻页效果可用不设
////        setPageTransformer(Transformer.DefaultTransformer);   // 集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//
//    }
//
//
//    //图片的点击事件
//    @Override
//    public void onItemClick(int position) {
//        String url = activityBean.getData().get(position).getUrl() + "";
////        Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
//        if (url.equals("") || url.length() < 11) {
//        } else {
//            context.startActivity(new Intent(context, WebViewActivity_new.class).putExtra("url", url));
//        }
//    }
//
//    //为了方便改写，来实现复杂布局的切换
//    private class LocalImageHolderView implements Holder<String> {
//        private ImageView imageView;
//
//        @Override
//        public View createView(Context context) {
//            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
//            imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            return imageView;
//        }
//
//        @Override
//        public void UpdateUI(Context context, int position, String data) {
////            final String url = data;
////            File filebanner = new File(Entity.path, "banner");
////            if (!filebanner.exists()) {
////                filebanner.mkdirs();
////            }
////            String[] list = filebanner.list();
////            File[] files = filebanner.listFiles();
////
////            ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(list));
////
////            if (arrayList.contains(url.substring(url.lastIndexOf("/") + 1))) {
////                myUtils.log("banner使用的本地图片", files[arrayList.indexOf(url.substring(url.lastIndexOf("/") + 1))].getPath());
////                imageView.setImageBitmap(BitmapFactory.decodeFile(files[arrayList.indexOf(url.substring(url.lastIndexOf("/") + 1))].getPath()));
////            } else {
////                Picasso.with(context)
////                        .load(data)
////                        .error(R.mipmap.home_fragment_banner)
////                        .into(imageView);
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        DownManager.getFileFromServer(url, Entity.path + "banner/", null);
////                    }
////                }).start();
////            }
//
////            Picasso.get()
////                    .load(data)
////                    .error(R.mipmap.home_fragment_banner)
////                    .into(imageView);
//            Glide.with(context)
//                    .load(data)
//                    .centerCrop()
//                    .placeholder(R.mipmap.home_fragment_banner)
//                    .into(imageView);
//        }
//
//
//    }
//
//    /**
//     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
//     *
//     * @param variableName
//     * @param c
//     * @return
//     */
//    public static int getResId(String variableName, Class<?> c) {
//        try {
//            Field idField = c.getDeclaredField(variableName);
//            return idField.getInt(idField);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//}
