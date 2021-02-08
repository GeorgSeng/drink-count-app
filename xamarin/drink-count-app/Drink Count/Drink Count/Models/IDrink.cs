using System;

namespace Drink_Count.Models
{
    public interface IDrink
    {
        public int Id { get; set; }
        public DateTime Time { get; set; }
        public DrinksEnum DrinkType { get; set; }
        public int Amount { get; set; }
    }

    public enum DrinksEnum
    {
        WATER,
        COFFEE,
    }
}
