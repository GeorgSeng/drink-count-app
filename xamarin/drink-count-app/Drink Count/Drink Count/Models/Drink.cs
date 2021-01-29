using SQLite;
using System;

namespace Drink_Count.Models
{
    public class Drink : IDrink
    {
        [PrimaryKey, AutoIncrement]
        public int Id { get; set; }
        public DateTime Time { get; set; }
        public int Amount { get; set; }
        public DrinksEnum DrinkType { get; set; }
        //[Ignore]
        //public string DrinkAndAmount { get { return $"{DrinkType}: {Amount} Cups"; } }
        [Ignore]
        public string DrinkFromattetForList { 
            get {
                return $"{Time.ToShortDateString()} {Time.ToShortTimeString()}    {DrinkType}: {Amount} Cups"; } }
    }
}
