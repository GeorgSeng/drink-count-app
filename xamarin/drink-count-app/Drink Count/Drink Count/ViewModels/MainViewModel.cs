using Drink_Count.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Diagnostics;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;

namespace Drink_Count.ViewModels
{
    public class MainViewModel : INotifyPropertyChanged
    {
        private DrinkViewModel drinkEdit;
        private bool isEditing;

        public IList<DrinkViewModel> Drinks { get; } = new ObservableCollection<DrinkViewModel>();

        #region Properties
        public DrinkViewModel DrinkEdit
        {
            get { return drinkEdit; }
            set { SetProperty(ref drinkEdit, value); }
        }
        public bool IsEditing
        {
            get { return isEditing; }
            set { SetProperty(ref isEditing, value); }
        }
        #endregion

        public event PropertyChangedEventHandler PropertyChanged;

        #region Commands
        public ICommand NewCommand { get; private set; }
        public ICommand SubmitCommand { get; private set; }
        public ICommand CancelCommand { get; private set; }
        #endregion

        public MainViewModel()
        {

            Console.WriteLine("MainViewModel says Hello World! ");

            #region Commands
            NewCommand = new Command(
                execute: () =>
                {
                    DrinkEdit = new DrinkViewModel();
                    DrinkEdit.PropertyChanged += OnDrinkEditPropertyChanged;
                    IsEditing = true;
                    RefreshCanExecutes();
                },
                canExecute: () => { return !IsEditing; });

            SubmitCommand = new Command(
                execute: () =>
                {
                    DrinkEdit.PropertyChanged -= OnDrinkEditPropertyChanged;
                    Drinks.Add(DrinkEdit);
                    var temp = ConvertEditToDrink(DrinkEdit);
                    if (temp.Count > 0)
                        temp.ForEach(it => { App.Database.SaveItemAsync(it); });
                    DrinkEdit = null;
                    IsEditing = false;
                    RefreshCanExecutes();
                },
                canExecute: () =>
                {
                    return DrinkEdit != null &&
                           (DrinkEdit.CoffeeCount > 0 ||
                            DrinkEdit.WaterCount > 0);
                });

            CancelCommand = new Command(
                execute: () =>
                {
                    DrinkEdit.PropertyChanged -= OnDrinkEditPropertyChanged;
                    DrinkEdit = null;
                    IsEditing = false;
                    RefreshCanExecutes();
                },
                canExecute: () =>
                {
                    return IsEditing;
                });
            #endregion
        }

        private List<Drink> ConvertEditToDrink(DrinkViewModel viewModel)
        {
            var drinks = new List<Drink>();
            if (viewModel.CoffeeCount != 0)
            {
                drinks.Add(new Drink
                {
                    Time = DateTime.Now,
                    DrinkType = DrinksEnum.COFFEE,
                    Amount = viewModel.CoffeeCount
                });
            }

            if (viewModel.WaterCount != 0)
            {
                drinks.Add(new Drink
                {
                    Time = DateTime.Now,
                    DrinkType = DrinksEnum.WATER,
                    Amount = viewModel.WaterCount
                });
            }
            return drinks;
        }

        #region UI-Refresh
        void OnDrinkEditPropertyChanged(object sender, PropertyChangedEventArgs args)
        {
            (SubmitCommand as Command).ChangeCanExecute();
        }

        void RefreshCanExecutes()
        {
            (NewCommand as Command).ChangeCanExecute();
            (SubmitCommand as Command).ChangeCanExecute();
            (CancelCommand as Command).ChangeCanExecute();
        }
        #endregion

        #region Notify
        bool SetProperty<T>(ref T storage, T value, [CallerMemberName] string propertyName = null)
        {
            if (Object.Equals(storage, value))
                return false;

            storage = value;
            OnPropertyChanged(propertyName);
            return true;
        }

        protected void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
        #endregion
    }
}
