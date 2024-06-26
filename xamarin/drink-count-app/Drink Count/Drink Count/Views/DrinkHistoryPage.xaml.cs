﻿using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Drink_Count.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class DrinkHistoryPage : ContentPage
    {
        public ObservableCollection<string> Items { get; set; }

        public DrinkHistoryPage()
        {
            InitializeComponent();
        }

        protected override async void OnAppearing()
        {
            base.OnAppearing();

            MyListView.ItemsSource = (await App.Database.GetItemsAsync()).OrderByDescending(i=>i.Time);
        }

        //async void Handle_ItemTapped(object sender, ItemTappedEventArgs e)
        //{
        //    if (e.Item == null)
        //        return;

        //    await DisplayAlert("Item Tapped", "An item was tapped.", "OK");

        //    //Deselect Item
        //    ((ListView)sender).SelectedItem = null;
        //}
    }
}
