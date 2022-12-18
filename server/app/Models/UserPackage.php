<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class UserPackage extends Model
{
    use HasFactory;

    protected $guarded = [
        "created_at",
        "id",
        "updated_at"
    ];

    public function processor()
    {
        return $this->belongsTo(Processor::class);
    }

    public function vga()
    {
        return $this->belongsTo(Vga::class);
    }

    public function motherboard()
    {
        return $this->belongsTo(Motherboard::class);
    }

    public function power_supply()
    {
        return $this->belongsTo(PowerSupply::class);
    }
}
