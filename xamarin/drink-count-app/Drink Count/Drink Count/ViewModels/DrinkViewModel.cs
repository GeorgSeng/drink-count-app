using Drink_Count.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text;

namespace Drink_Count.ViewModels
{
    public class DrinkViewModel : INotifyPropertyChanged
    {
        private int coffeeCount;
        private int waterCount;

        public int CoffeeCount { 
            get { return coffeeCount; } 
            set { SetProperty(ref coffeeCount, value); } 
        }

        public int WaterCount {
            get { return waterCount; }
            set { SetProperty(ref waterCount, value); }
        }

        public event PropertyChangedEventHandler PropertyChanged;

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
    }
}
