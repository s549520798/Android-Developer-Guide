package com.lazylee.apiguidedemo.coretopics.ui_navigation.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.lazylee.apiguidedemo.R

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            NewAppWidgetConfigureActivity.Companion.deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                            appWidgetId: Int) {
            val widgetText: CharSequence = NewAppWidgetConfigureActivity.Companion.loadTitlePref(context, appWidgetId)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            views.setTextViewText(R.id.appwidget_id, appWidgetId.toString())

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}