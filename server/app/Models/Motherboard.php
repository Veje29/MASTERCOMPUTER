<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Motherboard extends Model
{
    use HasFactory;

        public function user_package()
    {
        return $this->belongsTo(UserPackage::class);
    }
}
