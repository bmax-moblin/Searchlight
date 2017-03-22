### Description
TextView that is animated in a manner that resembles a searchlight.

<img src="https://github.com/bmax-moblin/Searchlight/blob/master/demo.gif">

### Usage
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <com.moblin.widget.SearchlightTextView
        android:id="@+id/search_light_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="@string/app_name"
        android:textSize="@dimen/searchlight_text_size"
        android:textStyle="bold"
        app:centerColor="@color/searchlight_center_color"
        app:edgeColor="@color/searchlight_edge_color"/>

</FrameLayout>
```

